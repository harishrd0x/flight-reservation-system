//package com.version1.frs.service.impl;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.version1.frs.dto.ReviewRequest;
//import com.version1.frs.dto.ReviewResponse;
//import com.version1.frs.model.Flight;
//import com.version1.frs.model.Review;
//import com.version1.frs.model.User;
//import com.version1.frs.repository.FlightRepository;
//import com.version1.frs.repository.ReviewRepository;
//import com.version1.frs.repository.UserRepository;
//import com.version1.frs.service.ReviewService;
//
//@Service
//public class ReviewServiceImpl implements ReviewService {
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private FlightRepository flightRepository;
//
//    @Override
//    public ReviewResponse postReview(Long userId, ReviewRequest request) {
//        // Validate user and flight
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Flight flight = flightRepository.findById(request.getFlightId())
//                .orElseThrow(() -> new RuntimeException("Flight not found"));
//
//        // Create Review
//        Review review = new Review();
//        review.setUser(user);
//        review.setFlight(flight);
//        review.setRating(request.getRating());
//        review.setReviewText(request.getReviewText());
//
//        review = reviewRepository.save(review);
//
//        return mapToResponse(review);
//    }
//
//    @Override
//    public List<ReviewResponse> getReviewsByFlightId(Long flightId) {
//        List<Review> reviews = reviewRepository.findByFlight_FlightId(flightId);
//        return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ReviewResponse> getReviewsByUserId(Long userId) {
//        List<Review> reviews = reviewRepository.findByUser_UserId(userId);
//        return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ReviewResponse> getAllReviews() {
//        List<Review> reviews = reviewRepository.findAll(); // Using JpaRepository's findAll method
//        return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
//    }
//    
//    private ReviewResponse mapToResponse(Review review) {
//        ReviewResponse res = new ReviewResponse();
//        res.setReviewId(review.getReviewId());
//        res.setUserId(review.getUser().getUserId());
//        res.setFlightId(review.getFlight().getFlightId());
//        res.setRating(review.getRating());
//        res.setReviewText(review.getReviewText());
//        return res;
//    }
//}
