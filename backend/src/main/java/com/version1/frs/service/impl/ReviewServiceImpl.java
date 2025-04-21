package com.version1.frs.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.dto.ReviewResponse;
import com.version1.frs.model.Flight;
import com.version1.frs.model.Review;
import com.version1.frs.model.User;
import com.version1.frs.repository.BookingRepository;
import com.version1.frs.repository.FlightRepository;
import com.version1.frs.repository.ReviewRepository;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;
    
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public ReviewResponse postReview(Long userId, ReviewRequest request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if user is a customer
        if (user.getUserRole() == null || !user.getUserRole().equalsIgnoreCase("CUSTOMER")) {
            throw new RuntimeException("Only customers can post reviews");
        }

        Flight flight = flightRepository.findById(request.getFlightId())
            .orElseThrow(() -> new RuntimeException("Flight not found"));

        // Check if the user actually booked this flight
        boolean hasBooked = bookingRepository.existsByUser_UserIdAndFlight_Id(userId, flight.getId());
        if (!hasBooked) {
            throw new RuntimeException("You can only review flights you have booked.");
        }

        Review review = new Review();
        review.setUser(user);
        review.setFlight(flight);
        review.setRating(request.getRating());
        review.setReviewText(request.getReviewText());

        review = reviewRepository.save(review);
        return mapToResponse(review);
    }

    @Override
    public List<ReviewResponse> getReviewsByFlightId(Long flightId) {
        List<Review> reviews = reviewRepository.findByFlight_Id(flightId);
        return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> getReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUser_UserId(userId);
        return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll(); // Using JpaRepository's findAll method
        return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    
    private ReviewResponse mapToResponse(Review review) {
        ReviewResponse res = new ReviewResponse();
        res.setReviewId(review.getReviewId());
        res.setUserId(review.getUser().getUserId());
        res.setFlightId(review.getFlight().getId());
        res.setRating(review.getRating());
        res.setReviewText(review.getReviewText());
        return res;
    }
}
