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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) used to receive airport creation or update
 * requests. This class captures and validates client input when submitting
 * airport information.
 * 
 * <p>
 * Validation annotations ensure all fields are properly populated according to
 * business rules.
 * </p>
 *
 * @author YourName
 * @since 1.0
 */
public class AirportRequest {

	/**
	 * Unique airport code (e.g., 'LAX', 'JFK'). Must be between 3 and 10
	 * characters.
	 */
	@NotBlank(message = "Airport code cannot be blank")
	@Size(min = 3, max = 10, message = "Airport code must be between 3 and 10 characters")
	private String airportCode;

	/**
	 * Full name of the airport. Must be between 2 and 100 characters.
	 */
	@NotBlank(message = "Airport name cannot be blank")
	@Size(min = 2, max = 100, message = "Airport name must be between 2 and 100 characters")
	private String airportName;

	/**
	 * City where the airport is located. Must be between 2 and 100 characters.
	 */
	@NotBlank(message = "City cannot be blank")
	@Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
	private String airportCity;

	/**
	 * State where the airport is located. Must be between 2 and 100 characters.
	 */
	@NotBlank(message = "State cannot be blank")
	@Size(min = 2, max = 100, message = "State must be between 2 and 100 characters")
	private String airportState;

	/**
	 * Country where the airport is located. Must be between 2 and 100 characters.
	 */
	@NotBlank(message = "Country cannot be blank")
	@Size(min = 2, max = 100, message = "Country must be between 2 and 100 characters")
	private String airportCountry;

	// Getters and Setters

	/**
	 * Gets the airport code.
	 * 
	 * @return airport code
	 */
	public String getAirportCode() {
		return airportCode;
	}

	/**
	 * Sets the airport code.
	 * 
	 * @param airportCode the unique airport identifier
	 */
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	/**
	 * Gets the airport name.
	 * 
	 * @return airport name
	 */
	public String getAirportName() {
		return airportName;
	}

	/**
	 * Sets the airport name.
	 * 
	 * @param airportName full name of the airport
	 */
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	/**
	 * Gets the city of the airport.
	 * 
	 * @return city name
	 */
	public String getAirportCity() {
		return airportCity;
	}

	/**
	 * Sets the city of the airport.
	 * 
	 * @param airportCity name of the city
	 */
	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}

	/**
	 * Gets the state of the airport.
	 * 
	 * @return state name
	 */
	public String getAirportState() {
		return airportState;
	}

	/**
	 * Sets the state of the airport.
	 * 
	 * @param airportState name of the state
	 */
	public void setAirportState(String airportState) {
		this.airportState = airportState;
	}

	/**
	 * Gets the country of the airport.
	 * 
	 * @return country name
	 */
	public String getAirportCountry() {
		return airportCountry;
	}

	/**
	 * Sets the country of the airport.
	 * 
	 * @param airportCountry name of the country
	 */
	public void setAirportCountry(String airportCountry) {
		this.airportCountry = airportCountry;
	}
}
