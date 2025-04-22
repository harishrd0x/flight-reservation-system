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

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.dto.FlightResponse;
import com.version1.frs.service.FlightService;

/**
 * Controller for managing flights. Supports flight addition, deletion,
 * retrieval, and searching.
 * 
 * Base URL: /api/flights
 */
@RestController
@RequestMapping("/api/flights")
@CrossOrigin
public class FlightController {

	private final FlightService flightService;

	/**
	 * Constructor for injecting the required {@link FlightService}.
	 *
	 * @param flightService the flight service to be used for flight operations
	 */
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	/**
	 * Adds a new flight to the system. Accessible only by ADMIN role.
	 *
	 * @param flightRequest the flight request DTO containing flight details
	 * @return the created flight as a response DTO
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<FlightResponse> addFlight(@RequestBody FlightRequest flightRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(flightService.addFlight(flightRequest));
	}

	/**
	 * Retrieves all flights available in the system. Accessible by both ADMIN and
	 * CUSTOMER roles.
	 *
	 * @return list of flight response DTOs
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	@GetMapping
	public ResponseEntity<List<FlightResponse>> getAllFlights() {
		return ResponseEntity.ok(flightService.getAllFlights());
	}

	/**
	 * Retrieves a specific flight by its ID. Accessible by both ADMIN and CUSTOMER
	 * roles.
	 *
	 * @param id the ID of the flight to retrieve
	 * @return flight response DTO if found
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	@GetMapping("/{id}")
	public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
		return ResponseEntity.ok(flightService.getFlightById(id));
	}

	/**
	 * Deletes a specific flight by its ID. Accessible only by ADMIN role. Returns
	 * 204 No Content on success or 404 Not Found if the flight doesn't exist.
	 *
	 * @param id the ID of the flight to delete
	 * @return response entity with appropriate status
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
		try {
			flightService.deleteFlight(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	/**
	 * Searches for flights based on source, destination, and date. Accessible by
	 * both ADMIN and CUSTOMER roles.
	 * 
	 * @param sourceId      ID of the source airport (optional)
	 * @param destinationId ID of the destination airport (optional)
	 * @param date          date of the flight in ISO format (optional)
	 * @return list of flights matching the search criteria
	 * @throws IllegalArgumentException if the given date is in the past
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	@GetMapping("/search")
	public ResponseEntity<List<FlightResponse>> searchFlights(@RequestParam(required = false) Long sourceId,
			@RequestParam(required = false) Long destinationId,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

		if (date != null && date.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("The flight date cannot be in the past.");
		}

		List<FlightResponse> flights = flightService.searchFlights(sourceId, destinationId, date);
		return ResponseEntity.ok(flights);
	}
}
