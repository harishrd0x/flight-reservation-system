package com.version1.frs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.model.Review;
import com.version1.frs.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> postReview(@RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.postReview(request));
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<Review>> getReviewsByFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(reviewService.getReviewsByFlight(flightId));
    }
}