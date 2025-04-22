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

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) used for receiving airplane-related data from
 * clients. This object is typically used in POST or PUT requests when creating
 * or updating an airplane. It includes validation constraints to ensure that
 * the input data meets the required rules.
 *
 * <p>
 * Fields include airplane number, name, model, manufacturer, and capacity. All
 * string fields are validated for non-blank input and length constraints.
 * Capacity must be a minimum of 1.
 * </p>
 *
 * @author YourName
 * @since 1.0
 */
public class AirplaneRequest {

	/**
	 * The unique identifier for the airplane. Cannot be blank and must be between 2
	 * and 50 characters.
	 */
	@NotBlank(message = "Airplane number cannot be blank")
	@Size(min = 2, max = 50, message = "Airplane number must be between 2 and 50 characters")
	private String airplaneNumber;

	/**
	 * The name of the airplane. Cannot be blank and must be between 2 and 100
	 * characters.
	 */
	@NotBlank(message = "Airplane name cannot be blank")
	@Size(min = 2, max = 100, message = "Airplane name must be between 2 and 100 characters")
	private String airplaneName;

	/**
	 * The model designation of the airplane. Cannot be blank and must be between 2
	 * and 50 characters.
	 */
	@NotBlank(message = "Airplane model cannot be blank")
	@Size(min = 2, max = 50, message = "Airplane model must be between 2 and 50 characters")
	private String airplaneModel;

	/**
	 * The manufacturer of the airplane. Cannot be blank and must be between 2 and
	 * 100 characters.
	 */
	@NotBlank(message = "Manufacturer cannot be blank")
	@Size(min = 2, max = 100, message = "Manufacturer name must be between 2 and 100 characters")
	private String manufacturer;

	/**
	 * The total passenger capacity of the airplane. Must be at least 1.
	 */
	@Min(value = 1, message = "Capacity must be at least 1")
	private int capacity;

	// Getters and Setters

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
	 * @param airplaneNumber the unique airplane identifier
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
	 * @param airplaneName the name of the airplane
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
	 * @param airplaneModel the model of the airplane
	 */
	public void setAirplaneModel(String airplaneModel) {
		this.airplaneModel = airplaneModel;
	}

	/**
	 * Gets the manufacturer name.
	 * 
	 * @return manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the manufacturer name.
	 * 
	 * @param manufacturer the name of the airplane's manufacturer
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Gets the airplane capacity.
	 * 
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the airplane capacity.
	 * 
	 * @param capacity the total number of seats
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
