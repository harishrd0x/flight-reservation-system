package com.version1.frs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.version1.frs.model.Review;

/**
 * Repository interface for managing {@link Review} entities. Provides CRUD
 * operations and custom queries related to reviews.
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {

	/**
	 * Retrieves all reviews for a specific flight.
	 *
	 * @param flightId the ID of the flight
	 * @return a list of reviews for the flight
	 */
	List<Review> findByFlight_Id(Long flightId);

	/**
	 * Retrieves all reviews submitted by a specific user.
	 *
	 * @param userId the ID of the user
	 * @return a list of reviews made by the user
	 */
	List<Review> findByUser_UserId(Long userId);

	/**
	 * Optional utility method to check if a user has already reviewed a flight.
	 * This can be used to prevent duplicate reviews in your service or controller
	 * layer.
	 *
	 * Uncomment and use if needed.
	 *
	 * @param userId   the ID of the user
	 * @param flightId the ID of the flight
	 * @return true if a review exists for the flight by the user, false otherwise
	 */
	// boolean existsByUser_UserIdAndFlight_FlightId(Long userId, Long flightId);
}
