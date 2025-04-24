package com.version1.frs.service;

import java.math.BigDecimal;
import java.util.List;

import com.version1.frs.dto.BookingRequest;
import com.version1.frs.dto.BookingResponse;

/**
 * Service interface for managing flight bookings in the system. It includes
 * methods for booking flights, retrieving bookings, and deleting bookings.
 */
public interface BookingService {

	// -------------------- Create --------------------

	/**
	 * Books a flight for a user.
	 * 
	 * @param request the {@link BookingRequest} containing flight and user details
	 * @param userId  the ID of the user booking the flight
	 * @return the {@link BookingResponse} DTO containing booking details
	 */
	BookingResponse bookFlight(BookingRequest request, Long userId);

	// -------------------- Read --------------------

	/**
	 * Retrieves all bookings made by a specific user.
	 * 
	 * @param userId the ID of the user whose bookings are to be retrieved
	 * @return a list of {@link BookingResponse} DTOs representing the user's
	 *         bookings
	 */
	List<BookingResponse> getBookingsByUser(Long userId);

	/**
	 * Retrieves all bookings. Optionally filters by customer ID.
	 * 
	 * @param customerId the ID of the customer to filter bookings by (optional)
	 * @return a list of {@link BookingResponse} DTOs representing all bookings
	 */
	List<BookingResponse> getAllBookings(Long customerId);

	/**
	 * Retrieves a specific booking by its ID.
	 * 
	 * @param id the ID of the booking to retrieve
	 * @return the {@link BookingResponse} DTO representing the booking
	 * @throws RuntimeException if no booking with the specified ID is found
	 */
	BookingResponse getBookingById(Long id);

	// -------------------- Delete --------------------

	/**
	 * Deletes a booking from the system using its ID.
	 * 
	 * @param id the ID of the booking to delete
	 * @return 
	 * @throws RuntimeException if no booking with the specified ID is found
	 */
	BigDecimal deleteBooking(Long id);
}
