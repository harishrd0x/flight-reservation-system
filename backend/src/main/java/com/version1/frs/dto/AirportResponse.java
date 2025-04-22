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

/**
 * Data Transfer Object (DTO) used to send airport information to the client.
 * Contains all relevant data returned from the backend about an airport.
 * 
 * <p>
 * This class is used in service and controller layers to format data for
 * external consumption while hiding internal implementation details.
 * </p>
 * 
 * @author YourName
 * @since 1.0
 */
public class AirportResponse {

	/**
	 * Internal system-generated airport ID (surrogate key).
	 */
	private Long airportId;

	/**
	 * Unique airport code (e.g., 'LAX', 'DEL').
	 */
	private String airportCode;

	/**
	 * Full name of the airport.
	 */
	private String airportName;

	/**
	 * City in which the airport is located.
	 */
	private String airportCity;

	/**
	 * State in which the airport is located.
	 */
	private String airportState;

	/**
	 * Country in which the airport is located.
	 */
	private String airportCountry;

	// Getters and Setters

	/**
	 * Gets the system-generated airport ID.
	 * 
	 * @return airport ID
	 */
	public Long getAirportId() {
		return airportId;
	}

	/**
	 * Sets the airport ID.
	 * 
	 * @param airportId system-generated ID
	 */
	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

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
	 * @param airportCode unique identifier
	 */
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	/**
	 * Gets the airport name.
	 * 
	 * @return name of the airport
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
	 * Gets the city where the airport is located.
	 * 
	 * @return city name
	 */
	public String getAirportCity() {
		return airportCity;
	}

	/**
	 * Sets the airport city.
	 * 
	 * @param airportCity name of the city
	 */
	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}

	/**
	 * Gets the state where the airport is located.
	 * 
	 * @return state name
	 */
	public String getAirportState() {
		return airportState;
	}

	/**
	 * Sets the airport state.
	 * 
	 * @param airportState name of the state
	 */
	public void setAirportState(String airportState) {
		this.airportState = airportState;
	}

	/**
	 * Gets the country where the airport is located.
	 * 
	 * @return country name
	 */
	public String getAirportCountry() {
		return airportCountry;
	}

	/**
	 * Sets the airport country.
	 * 
	 * @param airportCountry name of the country
	 */
	public void setAirportCountry(String airportCountry) {
		this.airportCountry = airportCountry;
	}
}
