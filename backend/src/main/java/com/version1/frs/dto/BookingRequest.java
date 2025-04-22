package com.version1.frs.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookingRequest {

	@NotNull(message = "Flight ID is required")
	private Long flightId;

	@NotEmpty(message = "Passenger list cannot be empty")
	private List<PassengerRequest> passengers;

	// Getters & Setters
	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public List<PassengerRequest> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerRequest> passengers) {
		this.passengers = passengers;
	}
}
