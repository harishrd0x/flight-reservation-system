package com.version1.frs.service;

import java.util.List;

import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.model.Review;

public interface ReviewService {

    String postReview(ReviewRequest request);

    List<Review> getReviewsByFlightId(Long flightId);
}