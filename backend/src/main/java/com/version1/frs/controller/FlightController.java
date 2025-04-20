package com.version1.frs.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/flights")
@CrossOrigin
public class FlightController {

	// AUTHORIZATION IS PENDING..

	@Autowired
	private FlightService flightService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<FlightResponse> addFlight(@RequestBody FlightRequest flightRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(flightService.addFlight(flightRequest));
	}

	@GetMapping
	public ResponseEntity<List<FlightResponse>> getAllFlights() {
		return ResponseEntity.ok(flightService.getAllFlights());
	}

	@GetMapping("/{id}")
	public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
		return ResponseEntity.ok(flightService.getFlightById(id));
	}

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

	// Endpoint for searching flights
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
