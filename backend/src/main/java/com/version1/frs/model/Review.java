package com.version1.frs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing a review submitted by a user for a flight. Maps to
 * the TBL_REVIEWS table in the database. Each review contains a rating,
 * optional review text, and associations to both user and flight.
 */
@Entity
@Table(name = "TBL_REVIEWS")
public class Review {

	// -------------------- Fields --------------------

	/**
	 * Unique identifier for the review. Mapped to the 'REVIEW_ID' column.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REVIEW_ID")
	private Long reviewId;

	/**
	 * The user who submitted the review. Represents a many-to-one relationship with
	 * {@link User}.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "USER_ID")
	private User user;

	/**
	 * The flight being reviewed. Represents a many-to-one relationship with
	 * {@link Flight}.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "FLIGHT_ID")
	private Flight flight;

	/**
	 * The rating given to the flight by the user. Mapped to the 'RATING' column.
	 */
	@Column(name = "RATING", nullable = false)
	private Float rating;

	/**
	 * Optional textual feedback provided by the user. Mapped to the 'REVIEW_TEXT'
	 * column, max length 500.
	 */
	@Column(name = "REVIEW_TEXT", length = 500)
	private String reviewText;

	// -------------------- Getters and Setters --------------------

	/**
	 * Gets the review ID.
	 *
	 * @return the review ID
	 */
	public Long getReviewId() {
		return reviewId;
	}

	/**
	 * Sets the review ID.
	 *
	 * @param reviewId the review ID to set
	 */
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	/**
	 * Gets the user who submitted the review.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user who submitted the review.
	 *
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the flight associated with this review.
	 *
	 * @return the flight
	 */
	public Flight getFlight() {
		return flight;
	}

	/**
	 * Sets the flight associated with this review.
	 *
	 * @param flight the flight to set
	 */
	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	/**
	 * Gets the rating given by the user.
	 *
	 * @return the rating
	 */
	public Float getRating() {
		return rating;
	}

	/**
	 * Sets the rating given by the user.
	 *
	 * @param rating the rating to set
	 */
	public void setRating(Float rating) {
		this.rating = rating;
	}

	/**
	 * Gets the review text submitted by the user.
	 *
	 * @return the review text
	 */
	public String getReviewText() {
		return reviewText;
	}

	/**
	 * Sets the review text submitted by the user.
	 *
	 * @param reviewText the review text to set
	 */
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
}
