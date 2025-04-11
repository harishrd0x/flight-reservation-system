package com.version1.frs.service;
 
import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.model.Review;
 
import java.util.List;
 
public interface ReviewService {
 
    String postReview(ReviewRequest request);
 
    List<Review> getReviewsByFlight(Long flightId);
}