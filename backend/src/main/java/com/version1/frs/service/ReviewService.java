package com.version1.frs.service;

import java.util.List;

import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.dto.ReviewResponse;

/**
 * Service interface for managing reviews in the system. It includes methods for
 * posting reviews, retrieving reviews by flight or user, and getting all
 * reviews.
 */
public interface ReviewService {

	// -------------------- Create --------------------

	/**
	 * Allows a user to post a review for a flight.
	 * 
	 * @param userId  the ID of the user posting the review
	 * @param request the {@link ReviewRequest} DTO containing the review details
	 *                (rating, text)
	 * @return the {@link ReviewResponse} DTO representing the posted review
	 * @throws RuntimeException if the user is not a customer, or if the flight is
	 *                          not booked by the user
	 */
	ReviewResponse postReview(Long userId, ReviewRequest request);

	// -------------------- Read --------------------

	/**
	 * Retrieves all reviews for a specific flight by its ID.
	 * 
	 * @param flightId the ID of the flight for which reviews are being retrieved
	 * @return a list of {@link ReviewResponse} DTOs representing the reviews for
	 *         the flight
	 */
	List<ReviewResponse> getReviewsByFlightId(Long flightId);

	/**
	 * Retrieves all reviews posted by a specific user by their ID.
	 * 
	 * @param userId the ID of the user whose reviews are being retrieved
	 * @return a list of {@link ReviewResponse} DTOs representing the reviews posted
	 *         by the user
	 */
	List<ReviewResponse> getReviewsByUserId(Long userId);

	/**
	 * Retrieves all reviews in the system.
	 * 
	 * @return a list of {@link ReviewResponse} DTOs representing all reviews
	 */
	List<ReviewResponse> getAllReviews();
}
