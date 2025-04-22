/*
 * Copyright 2025 Version 1
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.version1.frs.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.dto.ReviewResponse;
import com.version1.frs.security.UserDetailsImpl;
import com.version1.frs.service.ReviewService;

/**
 * Controller for managing reviews. Allows customers to post and view reviews,
 * and admins to access reviews.
 * 
 * Base URL: /api/reviews
 */
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	/**
	 * Constructor for injecting the required {@link ReviewService}.
	 *
	 * @param reviewService the review service to be used for review operations
	 */
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	/**
	 * Allows a customer to post a review. Accessible only by customers
	 * (ROLE_CUSTOMER).
	 * 
	 * @param request     the review request DTO containing review details
	 * @param userDetails the authenticated user details to get the customer ID
	 * @return the saved review response DTO
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping
	public ResponseEntity<ReviewResponse> postReview(@RequestBody ReviewRequest request,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		Long userId = userDetails.getId(); // get authenticated customer ID
		ReviewResponse saved = reviewService.postReview(userId, request);

		return ResponseEntity.ok(saved);
	}

	/**
	 * Retrieves all reviews for a specific flight. Accessible by both ADMIN and
	 * CUSTOMER roles.
	 * 
	 * @param flightId the ID of the flight for which reviews are requested
	 * @return list of review response DTOs for the specified flight
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	@GetMapping("/flight/{flightId}")
	public List<ReviewResponse> getReviewsByFlightId(@PathVariable Long flightId) {
		return reviewService.getReviewsByFlightId(flightId);
	}

	/**
	 * Retrieves all reviews posted by the current authenticated customer.
	 * Accessible only by customers (ROLE_CUSTOMER).
	 * 
	 * @param userDetails the authenticated user details to get the customer ID
	 * @return list of review response DTOs for the authenticated customer
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/my")
	public List<ReviewResponse> getReviewsByCurrentCustomer(@AuthenticationPrincipal UserDetailsImpl userDetails) {

		Long userId = userDetails.getId();
		return reviewService.getReviewsByUserId(userId);
	}

	/**
	 * Retrieves all reviews in the system, intended for public viewing. Accessible
	 * to all (public endpoint).
	 * 
	 * @return list of all review response DTOs
	 */
	@GetMapping
	public List<ReviewResponse> getAllReviews() {
		return reviewService.getAllReviews();
	}
}
