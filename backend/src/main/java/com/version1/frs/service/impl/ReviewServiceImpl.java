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

    @Override
    public ReviewResponse postReview(ReviewRequest request) {
    	User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Review review = new Review();
        review.setUser(user);
        review.setFlight(flight);
        review.setRating(request.getRating());
        review.setReviewText(request.getReviewText());

        Review saved = reviewRepository.save(review);
        return mapToDto(saved);
    }

    @Override
    public List<ReviewResponse> getReviewsByFlight(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        return reviewRepository.findByFlight(flight)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ReviewResponse mapToDto(Review review) {
        ReviewResponse dto = new ReviewResponse();
        dto.setReviewId(review.getReviewId());
        dto.setUserId((long) review.getUser().getUserId());
        dto.setFlightId(review.getFlight().getId());
        dto.setRating(review.getRating());
        dto.setReviewText(review.getReviewText());
        return dto;
    }
}