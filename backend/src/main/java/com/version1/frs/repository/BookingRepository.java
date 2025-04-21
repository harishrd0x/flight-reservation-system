package com.version1.frs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.version1.frs.model.Booking;

/**
 * Repository interface for managing {@link Booking} entities. Provides CRUD
 * operations and custom query methods for booking-related data.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

	/**
	 * Retrieves a list of bookings associated with a specific user by user ID.
	 *
	 * @param userId the ID of the user whose bookings are to be retrieved
	 * @return a list of bookings made by the user
	 */
	List<Booking> findByUserUserId(Long userId);

	/**
	 * Checks if a user has made a booking for a specific flight.
	 *
	 * @param userId   the ID of the user
	 * @param flightId the ID of the flight
	 * @return true if the user has made a booking for the flight, false otherwise
	 */
	boolean existsByUser_UserIdAndFlight_Id(Long userId, Long flightId);
}
