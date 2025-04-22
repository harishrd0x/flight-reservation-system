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

/**
 * DTO for returning flight information to clients. Includes flight details such
 * as airline, times, airport IDs/names, and pricing. Used typically in API
 * responses after flight searches or bookings.
 * 
 * @author YourName
 * @since 1.0
 */
public class FlightResponse {

	private Long id;
	private String airline;
	private String departureTime;
	private String arrivalTime;
	private Long fromAirportId;
	private String fromAirportName;
	private Long toAirportId;
	private String toAirportName;
	private BigDecimal price;

	// Getters and Setters

	/**
	 * Gets the flight ID.
	 * 
	 * @return flight ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the flight ID.
	 * 
	 * @param id the flight ID to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the airline name.
	 * 
	 * @return airline name
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

	/**
	 * Gets the flight's departure time (as a formatted string).
	 * 
	 * @return departure time
	 */
	public String getDepartureTime() {
		return departureTime;
	}

	/**
	 * Sets the flight's departure time.
	 * 
	 * @param departureTime the departure time to set
	 */
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * Gets the flight's arrival time (as a formatted string).
	 * 
	 * @return arrival time
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the flight's arrival time.
	 * 
	 * @param arrivalTime the arrival time to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Gets the departure airport ID.
	 * 
	 * @return fromAirportId
	 */
	public Long getFromAirportId() {
		return fromAirportId;
	}

	/**
	 * Sets the departure airport ID.
	 * 
	 * @param fromAirportId the ID to set
	 */
	public void setFromAirportId(Long fromAirportId) {
		this.fromAirportId = fromAirportId;
	}

	/**
	 * Gets the name of the departure airport.
	 * 
	 * @return fromAirportName
	 */
	public String getFromAirportName() {
		return fromAirportName;
	}

	/**
	 * Sets the name of the departure airport.
	 * 
	 * @param fromAirportName the name to set
	 */
	public void setFromAirportName(String fromAirportName) {
		this.fromAirportName = fromAirportName;
	}

	/**
	 * Gets the arrival airport ID.
	 * 
	 * @return toAirportId
	 */
	public Long getToAirportId() {
		return toAirportId;
	}

	/**
	 * Sets the arrival airport ID.
	 * 
	 * @param toAirportId the ID to set
	 */
	public void setToAirportId(Long toAirportId) {
		this.toAirportId = toAirportId;
	}

	/**
	 * Gets the name of the arrival airport.
	 * 
	 * @return toAirportName
	 */
	public String getToAirportName() {
		return toAirportName;
	}

	/**
	 * Sets the name of the arrival airport.
	 * 
	 * @param toAirportName the name to set
	 */
	public void setToAirportName(String toAirportName) {
		this.toAirportName = toAirportName;
	}

	/**
	 * Gets the price of the flight.
	 * 
	 * @return flight price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price of the flight.
	 * 
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
