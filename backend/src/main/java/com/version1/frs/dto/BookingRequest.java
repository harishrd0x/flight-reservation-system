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

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for booking a flight. Contains the flight ID
 * required to initiate a booking request.
 *
 * <p>
 * This DTO is typically used by the client when requesting a new booking,
 * providing only the necessary reference to the selected flight.
 * </p>
 * 
 * @author YourName
 * @since 1.0
 */
public class BookingRequest {

	/**
	 * ID of the selected flight to be booked. Must not be null.
	 */
	@NotNull(message = "Flight ID is required")
	private Long flightId;
  
  @NotEmpty(message = "Passenger list cannot be empty")
	private List<PassengerRequest> passengers;

	// Getters & Setters

	/**
	 * Gets the ID of the flight.
	 * 
	 * @return flight ID
	 */
	public Long getFlightId() {
		return flightId;
	}

	/**
	 * Sets the ID of the flight.
	 * 
	 * @param flightId ID of the flight to be booked
	 */
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
