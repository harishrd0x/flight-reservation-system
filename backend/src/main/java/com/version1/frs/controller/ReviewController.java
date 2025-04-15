package com.version1.frs.controller;

import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.dto.ReviewResponse;
import com.version1.frs.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // CUSTOMER: Post a review
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<ReviewResponse> postReview(@RequestBody ReviewRequest request) {
        ReviewResponse saved = reviewService.postReview(request);
        return ResponseEntity.ok(saved);
    }

    // SHARED: Get reviews by flight ID
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/flight/{flightId}")
    public List<ReviewResponse> getReviewsByFlightId(@PathVariable Long flightId) {
        return reviewService.getReviewsByFlightId(flightId);
    }

    // PUBLIC/SHARED: Get all reviews (for homepage display)
    @GetMapping
    public List<ReviewResponse> getAllReviews() {
        return reviewService.getAllReviews();
    }
}