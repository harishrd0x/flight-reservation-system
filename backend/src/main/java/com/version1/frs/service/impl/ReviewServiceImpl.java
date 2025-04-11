package com.version1.frs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.model.Review;
import com.version1.frs.repository.ReviewRepository;
import com.version1.frs.service.ReviewService;

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
        review.setComment(request.getComment());

        reviewRepository.save(review);
        return "Review posted successfully.";
    }

    @Override
    public List<Review> getReviewsByFlightId(Long flightId) {
        return reviewRepository.findByFlightId(flightId);
    }
}