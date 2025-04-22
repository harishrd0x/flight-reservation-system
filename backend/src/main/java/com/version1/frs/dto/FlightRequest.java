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

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) used to encapsulate flight creation or update
 * requests.
 * 
 * <p>
 * Includes flight details such as airplane, departure/arrival times, airports,
 * price, and the airline name. All fields are validated for presence.
 * </p>
 * 
 * @author YourName
 * @since 1.0
 */
public class FlightRequest {

	/**
	 * Identifier for the airplane used in this flight.
	 */
	@NotNull(message = "Airplane is required")
	private Long airplaneId;

	/**
	 * Departure timestamp of the flight.
	 */
	@NotNull(message = "Departure time is required")
	private LocalDateTime departureTime;

	/**
	 * Arrival timestamp of the flight.
	 */
	@NotNull(message = "Arrival time is required")
	private LocalDateTime arrivalTime;

	/**
	 * Identifier for the airport from which the flight departs.
	 */
	@NotNull(message = "Departure airport ID is required")
	private Long departureAirportId;

	/**
	 * Identifier for the airport where the flight arrives.
	 */
	@NotNull(message = "Arrival airport ID is required")
	private Long arrivalAirportId;

	/**
	 * Price of the flight ticket.
	 */
	@NotNull(message = "Price is required")
	private BigDecimal price;

	/**
	 * Name of the airline operating the flight.
	 */
	@NotNull(message = "Airline is required")
	private String airline;

	// Getters and Setters

	/**
	 * Gets the airplane ID.
	 * 
	 * @return airplaneId
	 */
	public Long getAirplaneId() {
		return airplaneId;
	}

	/**
	 * Sets the airplane ID.
	 * 
	 * @param airplaneId the airplane ID to set
	 */
	public void setAirplaneId(Long airplaneId) {
		this.airplaneId = airplaneId;
	}

	/**
	 * Gets the departure time.
	 * 
	 * @return departureTime
	 */
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	/**
	 * Sets the departure time.
	 * 
	 * @param departureTime the departure time to set
	 */
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * Gets the arrival time.
	 * 
	 * @return arrivalTime
	 */
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the arrival time.
	 * 
	 * @param arrivalTime the arrival time to set
	 */
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Gets the departure airport ID.
	 * 
	 * @return departureAirportId
	 */
	public Long getDepartureAirportId() {
		return departureAirportId;
	}

	/**
	 * Sets the departure airport ID.
	 * 
	 * @param departureAirportId the ID to set
	 */
	public void setDepartureAirportId(Long departureAirportId) {
		this.departureAirportId = departureAirportId;
	}

	/**
	 * Gets the arrival airport ID.
	 * 
	 * @return arrivalAirportId
	 */
	public Long getArrivalAirportId() {
		return arrivalAirportId;
	}

	/**
	 * Sets the arrival airport ID.
	 * 
	 * @param arrivalAirportId the ID to set
	 */
	public void setArrivalAirportId(Long arrivalAirportId) {
		this.arrivalAirportId = arrivalAirportId;
	}

	/**
	 * Gets the flight price.
	 * 
	 * @return price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the flight price.
	 * 
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the airline name.
	 * 
	 * @return airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Sets the airline name.
	 * 
	 * @param airline the airline name to set
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}
}
