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

package com.version1.frs.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.version1.frs.dto.BookingRequest;
import com.version1.frs.dto.BookingResponse;
import com.version1.frs.dto.PassengerResponse;
import com.version1.frs.model.BookedTicket;
import com.version1.frs.model.Booking;
import com.version1.frs.model.Flight;
import com.version1.frs.model.User;
import com.version1.frs.model.Wallet;
import com.version1.frs.repository.BookingRepository;
import com.version1.frs.repository.FlightRepository;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.repository.WalletRepository;
import com.version1.frs.service.BookingService;

import jakarta.transaction.Transactional;

/**
 * Implementation of the {@link BookingService} interface. Handles booking
 * operations, including creating bookings, retrieving booking details, and
 * managing wallet balances. Ensures transactional consistency when performing
 * booking and wallet deduction operations.
 */
@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final UserRepository userRepository;
	private final FlightRepository flightRepository;
	private final WalletRepository walletRepository;

	// Constructor injection for dependencies
	public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository,
			FlightRepository flightRepository, WalletRepository walletRepository) {
		this.bookingRepository = bookingRepository;
		this.userRepository = userRepository;
		this.flightRepository = flightRepository;
		this.walletRepository = walletRepository;
	}

	/**
	 * Books a flight for a user. Deducts the flight price from the user's wallet
	 * and saves the booking details. This operation is transactional to ensure
	 * consistency.
	 *
	 * @param request the booking request containing flight ID and user ID
	 * @param userId  the ID of the user making the booking
	 * @return the saved booking response
	 * @throws RuntimeException if user, flight, or wallet is not found, or if
	 *                          insufficient balance
	 */
	@Transactional
	@Override
	public BookingResponse bookFlight(BookingRequest request, Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Flight flight = flightRepository.findById(request.getFlightId())
				.orElseThrow(() -> new RuntimeException("Flight not found"));

		Wallet wallet = walletRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new RuntimeException("Wallet not found"));

		int numPassengers = request.getPassengers().size();
		BigDecimal totalAmount = flight.getPrice().multiply(BigDecimal.valueOf(numPassengers));

		if (wallet.getBalance().compareTo(totalAmount) < 0) {
			throw new RuntimeException("Insufficient wallet balance.");
		}

		wallet.setBalance(wallet.getBalance().subtract(totalAmount));
		walletRepository.save(wallet);

		Booking booking = new Booking();
		booking.setUser(user);
		booking.setFlight(flight);
		booking.setBookingTime(LocalDateTime.now());
		booking.setTotalAmount(totalAmount);

		List<BookedTicket> tickets = request.getPassengers().stream().map(p -> {
			BookedTicket ticket = new BookedTicket();
			ticket.setBooking(booking);
			ticket.setPassengerName(p.getPassengerName());
			ticket.setPassengerAge(p.getPassengerAge());
			ticket.setPassengerGender(p.getPassengerGender());
			return ticket;
		}).collect(Collectors.toList());

		booking.setBookedTickets(tickets);

		bookingRepository.save(booking);

		return mapToDto(booking);
	}

	/**
	 * Retrieves all bookings made by a specific user.
	 *
	 * @param userId the ID of the user whose bookings are to be retrieved
	 * @return a list of booking responses
	 */
	@Override
	public List<BookingResponse> getBookingsByUser(Long userId) {
		return bookingRepository.findByUserUserId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	/**
	 * Retrieves all bookings or filters them by a specific customer ID.
	 *
	 * @param customerId the ID of the customer to filter by (nullable)
	 * @return a list of booking responses
	 */
	@Override
	public List<BookingResponse> getAllBookings(Long customerId) {
		List<Booking> bookings;

		if (customerId != null) {
			bookings = bookingRepository.findByUserUserId(customerId);
		} else {
			bookings = bookingRepository.findAll();
		}

		return bookings.stream().map(this::mapToDto).collect(Collectors.toList());
	}

	/**
	 * Retrieves a booking by its unique ID.
	 *
	 * @param bookingId the ID of the booking
	 * @return the booking response
	 * @throws RuntimeException if the booking is not found
	 */
	@Override
	public BookingResponse getBookingById(Long bookingId) {
		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking not found"));
		return mapToDto(booking);
	}

	/**
	 * Deletes a booking by its ID.
	 *
	 * @param bookingId the ID of the booking to delete
	 * @throws RuntimeException if the booking is not found
	 */
	@Override
	@Transactional
	public BigDecimal deleteBooking(Long bookingId) {
	    Booking booking = bookingRepository.findById(bookingId)
	            .orElseThrow(() -> new RuntimeException("Booking not found"));

	    User user = booking.getUser();
	    Wallet wallet = walletRepository.findByUser_UserId(user.getUserId())
	            .orElseThrow(() -> new RuntimeException("Wallet not found"));

	    BigDecimal refundAmount = booking.getTotalAmount();
	    wallet.setBalance(wallet.getBalance().add(refundAmount));
	    walletRepository.save(wallet);

	    bookingRepository.delete(booking);

	    return refundAmount;
	}


	/**
	 * Converts a booking entity to a booking response DTO.
	 *
	 * @param booking the booking entity to convert
	 * @return the corresponding booking response DTO
	 */

	private BookingResponse mapToDto(Booking booking) {
		List<PassengerResponse> passengers = booking.getBookedTickets().stream()
				.map(t -> new PassengerResponse(t.getPassengerName(), t.getPassengerAge(), t.getPassengerGender()))
				.collect(Collectors.toList());

		return new BookingResponse(booking.getBookingId(), booking.getUser().getUserId(), booking.getFlight().getId(),
				booking.getBookingTime(), booking.getTotalAmount(), passengers);
	}

}
