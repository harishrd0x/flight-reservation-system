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

/**
 * DTO for the review response. Provides the details of a review submitted by a
 * user for a flight.
 * 
 * @author YourName
 * @since 1.0
 */
public class ReviewResponse {

	private Long reviewId;
	private Long userId;
	private Long flightId;
	private Float rating;
	private String reviewText;

	/**
	 * Gets the review ID.
	 * 
	 * @return review ID
	 */
	public Long getReviewId() {
		return reviewId;
	}

	/**
	 * Sets the review ID.
	 * 
	 * @param reviewId the review ID
	 */
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

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
	 * @param rating the rating value
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
	 * @param reviewText the review text
	 */
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
}
