//package com.version1.frs.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.version1.frs.dto.ReviewRequest;
//import com.version1.frs.dto.ReviewResponse;
//import com.version1.frs.security.UserDetailsImpl;
//import com.version1.frs.service.ReviewService;
//
//@RestController
//@RequestMapping("/api/reviews")
//public class ReviewController {
//
//    @Autowired
//    private ReviewService reviewService;
//
//    // CUSTOMER: Post a review
//    @PreAuthorize("hasRole('CUSTOMER')")
//    @PostMapping
//    public ResponseEntity<ReviewResponse> postReview(
//            @RequestBody ReviewRequest request,
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        Long userId = userDetails.getId(); // get authenticated customer ID
//        ReviewResponse saved = reviewService.postReview(userId, request);
//
//        return ResponseEntity.ok(saved);
//    }
//
//
//    // SHARED: Get reviews by flight ID
//    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
//    @GetMapping("/flight/{flightId}")
//    public List<ReviewResponse> getReviewsByFlightId(@PathVariable Long flightId) {
//        return reviewService.getReviewsByFlightId(flightId);
//    }
//    
//    // get reviews by customer id
//    @PreAuthorize("hasRole('CUSTOMER')")
//    @GetMapping("/my")
//    public List<ReviewResponse> getReviewsByCurrentCustomer(
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        Long userId = userDetails.getId();
//        return reviewService.getReviewsByUserId(userId);
//    }
//
//
//    // PUBLIC/SHARED: Get all reviews (for homepage display)
//    @GetMapping
//    public List<ReviewResponse> getAllReviews() {
//        return reviewService.getAllReviews();
//    }
//}