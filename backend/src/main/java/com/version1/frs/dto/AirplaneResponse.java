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
 * Data Transfer Object (DTO) for sending airplane data to the client. This
 * class represents a simplified and structured response format used in APIs to
 * return airplane details.
 *
 * <p>
 * Typically used in GET or POST responses after retrieving or saving an
 * airplane record.
 * </p>
 *
 * @author YourName
 * @since 1.0
 */
public class AirplaneResponse {

	/**
	 * Unique identifier of the airplane in the database.
	 */
	private Long airplaneId;

	/**
	 * Airplane's unique number or code.
	 */
	private String airplaneNumber;

	/**
	 * Name or designation of the airplane.
	 */
	private String airplaneName;

	/**
	 * Model of the airplane.
	 */
	private String airplaneModel;

	/**
	 * Manufacturer of the airplane.
	 */
	private String manufacturer;

	/**
	 * Passenger capacity of the airplane.
	 */
	private int capacity;

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
	 * @param airplaneId the database ID of the airplane
	 */
	public void setAirplaneId(Long airplaneId) {
		this.airplaneId = airplaneId;
	}

	/**
	 * Gets the airplane number.
	 * 
	 * @return airplane number
	 */
	public String getAirplaneNumber() {
		return airplaneNumber;
	}

	/**
	 * Sets the airplane number.
	 * 
	 * @param airplaneNumber unique airplane code
	 */
	public void setAirplaneNumber(String airplaneNumber) {
		this.airplaneNumber = airplaneNumber;
	}

	/**
	 * Gets the airplane name.
	 * 
	 * @return airplane name
	 */
	public String getAirplaneName() {
		return airplaneName;
	}

	/**
	 * Sets the airplane name.
	 * 
	 * @param airplaneName name of the airplane
	 */
	public void setAirplaneName(String airplaneName) {
		this.airplaneName = airplaneName;
	}

	/**
	 * Gets the airplane model.
	 * 
	 * @return airplane model
	 */
	public String getAirplaneModel() {
		return airplaneModel;
	}

	/**
	 * Sets the airplane model.
	 * 
	 * @param airplaneModel model name/number
	 */
	public void setAirplaneModel(String airplaneModel) {
		this.airplaneModel = airplaneModel;
	}

	/**
	 * Gets the manufacturer of the airplane.
	 * 
	 * @return manufacturer name
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the manufacturer of the airplane.
	 * 
	 * @param manufacturer name of the manufacturer
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Gets the capacity of the airplane.
	 * 
	 * @return number of passenger seats
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity of the airplane.
	 * 
	 * @param capacity total number of seats
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
