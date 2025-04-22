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

package com.version1.frs.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.dto.AirplaneResponse;
import com.version1.frs.model.Airplane;
import com.version1.frs.repository.AirplaneRepository;
import com.version1.frs.service.AirplaneService;

/**
 * Implementation of the {@link AirplaneService} interface. Provides methods for
 * managing airplane entities including CRUD operations and filters.
 */
@Service
public class AirplaneServiceImpl implements AirplaneService {

	private final AirplaneRepository airplaneRepository;

	/**
	 * Constructor-based injection for {@link AirplaneRepository}.
	 *
	 * @param airplaneRepository the repository used for airplane persistence
	 *                           operations
	 */
	public AirplaneServiceImpl(AirplaneRepository airplaneRepository) {
		this.airplaneRepository = airplaneRepository;
	}

	/**
	 * Adds a new airplane to the database.
	 *
	 * @param request the airplane request object containing airplane details
	 * @return the saved airplane response
	 * @throws RuntimeException if an airplane with the same number already exists
	 */
	@Override
	public AirplaneResponse addAirplane(AirplaneRequest request) {
		if (airplaneRepository.existsByAirplaneNumber(request.getAirplaneNumber())) {
			throw new RuntimeException("Airplane with this number already exists");
		}
		Airplane airplane = mapToEntity(request);
		return mapToResponse(airplaneRepository.save(airplane));
	}

	/**
	 * Retrieves all airplanes from the database.
	 *
	 * @return a list of airplane responses
	 */
	@Override
	public List<AirplaneResponse> getAllAirplanes() {
		return airplaneRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	/**
	 * Retrieves an airplane by its ID.
	 *
	 * @param id the airplane ID
	 * @return the corresponding airplane response
	 * @throws RuntimeException if the airplane is not found
	 */
	@Override
	public AirplaneResponse getAirplaneById(Long id) {
		Airplane airplane = airplaneRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Airplane not found with ID: " + id));
		return mapToResponse(airplane);
	}

	/**
	 * Retrieves an airplane by its number.
	 *
	 * @param airplaneNumber the airplane number
	 * @return the corresponding airplane response
	 * @throws RuntimeException if the airplane is not found
	 */
	@Override
	public AirplaneResponse getAirplaneByNumber(String airplaneNumber) {
		Airplane airplane = airplaneRepository.findByAirplaneNumber(airplaneNumber)
				.orElseThrow(() -> new RuntimeException("Airplane not found with number: " + airplaneNumber));
		return mapToResponse(airplane);
	}

	/**
	 * Updates an existing airplane using its ID.
	 *
	 * @param id      the ID of the airplane to update
	 * @param request the new airplane data
	 * @return the updated airplane response
	 * @throws RuntimeException if the airplane is not found
	 */
	@Override
	public AirplaneResponse updateAirplane(Long id, AirplaneRequest request) {
		Airplane airplane = airplaneRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Airplane not found with ID: " + id));
		updateEntity(airplane, request);
		return mapToResponse(airplaneRepository.save(airplane));
	}

	/**
	 * Updates an existing airplane using its airplane number.
	 *
	 * @param airplaneNumber the airplane number
	 * @param request        the new airplane data
	 * @return the updated airplane response
	 * @throws RuntimeException if the airplane is not found
	 */
	@Override
	public AirplaneResponse updateAirplane(String airplaneNumber, AirplaneRequest request) {
		Airplane airplane = airplaneRepository.findByAirplaneNumber(airplaneNumber)
				.orElseThrow(() -> new RuntimeException("Airplane not found with number: " + airplaneNumber));
		updateEntity(airplane, request);
		return mapToResponse(airplaneRepository.save(airplane));
	}

	/**
	 * Deletes an airplane by its ID.
	 *
	 * @param id the airplane ID
	 * @return a confirmation message
	 * @throws RuntimeException if the airplane is not found
	 */
	@Override
	public String deleteAirplane(Long id) {
		if (!airplaneRepository.existsById(id)) {
			throw new RuntimeException("Airplane not found with ID: " + id);
		}
		airplaneRepository.deleteById(id);
		return "Airplane deleted successfully.";
	}

	/**
	 * Deletes an airplane by its number.
	 *
	 * @param airplaneNumber the airplane number
	 * @return a confirmation message
	 * @throws RuntimeException if the airplane is not found
	 */
	@Override
	public String deleteAirplane(String airplaneNumber) {
		Airplane airplane = airplaneRepository.findByAirplaneNumber(airplaneNumber)
				.orElseThrow(() -> new RuntimeException("Airplane not found with number: " + airplaneNumber));
		airplaneRepository.delete(airplane);
		return "Airplane deleted successfully.";
	}

	/**
	 * Checks if an airplane number already exists in the database.
	 *
	 * @param airplaneNumber the airplane number to check
	 * @return true if exists, false otherwise
	 */
	@Override
	public boolean airplaneNumberExists(String airplaneNumber) {
		return airplaneRepository.existsByAirplaneNumber(airplaneNumber);
	}

	/**
	 * Searches airplanes by a keyword in their name (case-insensitive).
	 *
	 * @param keyword the search keyword
	 * @return a list of matching airplane responses
	 */
	@Override
	public List<AirplaneResponse> searchByName(String keyword) {
		return airplaneRepository.findByAirplaneNameContainingIgnoreCase(keyword).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Filters airplanes by manufacturer (case-insensitive).
	 *
	 * @param manufacturer the manufacturer name
	 * @return a list of filtered airplane responses
	 */
	@Override
	public List<AirplaneResponse> filterByManufacturer(String manufacturer) {
		return airplaneRepository.findByManufacturerIgnoreCase(manufacturer).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Filters airplanes by model (case-insensitive).
	 *
	 * @param model the airplane model
	 * @return a list of filtered airplane responses
	 */
	@Override
	public List<AirplaneResponse> filterByModel(String model) {
		return airplaneRepository.findByAirplaneModelIgnoreCase(model).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Finds airplanes with capacity greater than or equal to the specified value.
	 *
	 * @param minCapacity the minimum capacity
	 * @return a list of matching airplane responses
	 */
	@Override
	public List<AirplaneResponse> findByCapacityGreaterThanEqual(int minCapacity) {
		return airplaneRepository.findByCapacityGreaterThanEqual(minCapacity).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Finds airplanes with capacity between the specified range.
	 *
	 * @param min the minimum capacity
	 * @param max the maximum capacity
	 * @return a list of matching airplane responses
	 */
	@Override
	public List<AirplaneResponse> findByCapacityBetween(int min, int max) {
		return airplaneRepository.findByCapacityBetween(min, max).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Converts a request DTO to an airplane entity.
	 *
	 * @param request the airplane request DTO
	 * @return the populated airplane entity
	 */
	private Airplane mapToEntity(AirplaneRequest request) {
		Airplane airplane = new Airplane();
		updateEntity(airplane, request);
		return airplane;
	}

	/**
	 * Updates the fields of an existing airplane entity using data from the
	 * request.
	 *
	 * @param airplane the airplane entity to update
	 * @param request  the request DTO containing updated data
	 */
	private void updateEntity(Airplane airplane, AirplaneRequest request) {
		airplane.setAirplaneName(request.getAirplaneName());
		airplane.setAirplaneNumber(request.getAirplaneNumber());
		airplane.setAirplaneModel(request.getAirplaneModel());
		airplane.setManufacturer(request.getManufacturer());
		airplane.setCapacity(request.getCapacity());
	}

	/**
	 * Converts an airplane entity to a response DTO.
	 *
	 * @param airplane the airplane entity
	 * @return the airplane response DTO
	 */
	private AirplaneResponse mapToResponse(Airplane airplane) {
		AirplaneResponse response = new AirplaneResponse();
		response.setAirplaneId(airplane.getAirplaneId());
		response.setAirplaneName(airplane.getAirplaneName());
		response.setAirplaneNumber(airplane.getAirplaneNumber());
		response.setAirplaneModel(airplane.getAirplaneModel());
		response.setManufacturer(airplane.getManufacturer());
		response.setCapacity(airplane.getCapacity());
		return response;
	}
}
