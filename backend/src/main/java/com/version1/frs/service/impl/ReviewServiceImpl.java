package com.version1.frs.service.impl;
 
import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.model.Review;
import com.version1.frs.repository.ReviewRepository;
import com.version1.frs.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class ReviewServiceImpl implements ReviewService {
 
    @Autowired
    private ReviewRepository reviewRepository;
 
    @Override
    public String postReview(ReviewRequest request) {
        Review review = new Review();
        review.setFlightId(request.getFlightId());
        review.setUserId(request.getUserId());
        review.setRating(request.getRating());
        review.setReviewText(request.getReviewText());
 
        reviewRepository.save(review);
        return "Review submitted successfully.";
    }
 
    @Override
    public List<Review> getReviewsByFlight(Long flightId) {
        return reviewRepository.findByFlightId(flightId);
    }
}