/*
 * Copyright 2022-2025 the original author or authors.
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

package com.version1.frs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for capturing review submission details. Used by users to submit ratings
 * and feedback for a flight.
 * 
 * Fields are validated for proper constraints.
 * 
 * @author YourName
 * @since 1.0
 */
public class ReviewRequest {

	@NotNull(message = "User ID cannot be null")
	private Long userId;

	@NotNull(message = "Flight ID cannot be null")
	private Long flightId;

	@Min(value = 1, message = "Rating must be at least 1")
	@Max(value = 5, message = "Rating cannot be more than 5")
	private Float rating;

	@Size(max = 500, message = "Review text can have a maximum of 500 characters")
	@NotBlank(message = "Review text cannot be empty")
	private String reviewText;

	/**
	 * Gets the user ID.
	 * 
	 * @return user ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID.
	 * 
	 * @param userId the user ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the flight ID.
	 * 
	 * @return flight ID
	 */
	public Long getFlightId() {
		return flightId;
	}

	/**
	 * Sets the flight ID.
	 * 
	 * @param flightId the flight ID
	 */
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	/**
	 * Gets the review rating.
	 * 
	 * @return rating
	 */
	public Float getRating() {
		return rating;
	}

	/**
	 * Sets the review rating.
	 * 
	 * @param rating the rating value between 1 and 5
	 */
	public void setRating(Float rating) {
		this.rating = rating;
	}

	/**
	 * Gets the review text.
	 * 
	 * @return review text
	 */
	public String getReviewText() {
		return reviewText;
	}

	/**
	 * Sets the review text.
	 * 
	 * @param reviewText the user review (max 500 chars)
	 */
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
}
