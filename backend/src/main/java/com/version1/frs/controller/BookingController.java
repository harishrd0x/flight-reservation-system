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

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.ApiResponse;
import com.version1.frs.dto.BookingRequest;
import com.version1.frs.dto.BookingResponse;
import com.version1.frs.security.UserDetailsImpl;
import com.version1.frs.service.BookingService;

import jakarta.validation.Valid;

/**
 * Controller for managing flight bookings. Allows customers to book, view, and
 * delete their own bookings. Admins can access all bookings.
 *
 * Base URL: /api/bookings
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService bookingService;

	/**
	 * Constructor for injecting the required {@link BookingService}.
	 *
	 * @param bookingService the booking service used for booking operations
	 */
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	/**
	 * Books a flight for the authenticated customer. Accessible only by users with
	 * the CUSTOMER role.
	 *
	 * @param request     the booking request containing flight and passenger
	 *                    details
	 * @param userDetails the authenticated user's details
	 * @return a booking confirmation response
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping
	public ResponseEntity<BookingResponse> bookFlight(@Valid @RequestBody BookingRequest request,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		Long userId = userDetails.getId();
		return ResponseEntity.ok(bookingService.bookFlight(request, userId));
	}


	/**
	 * Retrieves all bookings for the authenticated customer. Accessible only by
	 * users with the CUSTOMER role.
	 *
	 * @param userDetails the authenticated user's details
	 * @return a list of bookings for the user
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/user")
	public ResponseEntity<List<BookingResponse>> getUserBookings(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		Long userId = userDetails.getId();
		return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
	}

	/**
	 * Retrieves all bookings in the system. Accessible only by users with the ADMIN
	 * role. Optionally filters bookings by customerId.
	 *
	 * @param customerId optional query parameter to filter bookings by customer ID
	 * @return a list of bookings or an empty list
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<BookingResponse>> getAllBookings(@RequestParam(required = false) Long customerId) {
		return ResponseEntity.ok(bookingService.getAllBookings(customerId));
	}

	/**
	 * Retrieves a specific booking by its ID. Accessible by: - ADMIN: can view any
	 * booking - CUSTOMER: can only view their own bookings
	 *
	 * @param id          the ID of the booking
	 * @param userDetails the authenticated user's details
	 * @return the booking if found and authorized, otherwise 403 Forbidden
	 */
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		BookingResponse booking = bookingService.getBookingById(id);

		boolean isCustomer = userDetails.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"));

		if (isCustomer && !booking.getCustomerId().equals(userDetails.getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		return ResponseEntity.ok(booking);
	}

	/**
	 * Deletes a booking by ID. Accessible by: - CUSTOMER: only if the booking
	 * belongs to them - ADMIN: can delete any booking
	 *
	 * @param id the ID of the booking
	 * @return HTTP 204 No Content if successful
	 */
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);

		BigDecimal refundAmount = bookingService.deleteBooking(id);
		String msg = "Booking cancelled and ₹" + refundAmount + " refunded to your wallet.";
		return ResponseEntity.ok(new ApiResponse(msg));
	}
}
