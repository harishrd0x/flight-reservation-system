package com.version1.frs.service;

import java.util.List;

import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.dto.ReviewResponse;

public interface ReviewService {
    ReviewResponse postReview(ReviewRequest request);
    List<ReviewResponse> getReviewsByFlight(Long flightId);
    List<ReviewResponse> getAllReviews(); // For homepage display
}