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

package com.version1.frs.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.dto.AirplaneResponse;
import com.version1.frs.service.AirplaneService;

import jakarta.validation.Valid;

/**
 * Controller for managing airplane entities. Handles all HTTP endpoints related
 * to airplane creation, retrieval, updating, deletion, validation, and
 * filtering.
 * 
 * Base URL: /api/airplanes
 */
@RestController
@RequestMapping("/api/airplanes")
@CrossOrigin
public class AirplaneController {

	private final AirplaneService airplaneService;

	/**
	 * Constructor-based injection for {@link AirplaneService}.
	 *
	 * @param airplaneService the service used to handle airplane operations
	 */
	public AirplaneController(AirplaneService airplaneService) {
		this.airplaneService = airplaneService;
	}

	/**
	 * Adds a new airplane to the system. Only accessible by users with ADMIN role.
	 *
	 * @param airplaneRequest the airplane data to add
	 * @return the added airplane's response object
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<AirplaneResponse> addAirplane(@Valid @RequestBody AirplaneRequest airplaneRequest) {
		return ResponseEntity.ok(airplaneService.addAirplane(airplaneRequest));
	}

	/**
	 * Retrieves all airplanes in the system.
	 *
	 * @return list of all airplane response objects
	 */
	@GetMapping
	public ResponseEntity<List<AirplaneResponse>> getAllAirplanes() {
		return ResponseEntity.ok(airplaneService.getAllAirplanes());
	}

	/**
	 * Retrieves an airplane by its unique ID.
	 *
	 * @param id the ID of the airplane
	 * @return the airplane response object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<AirplaneResponse> getAirplaneById(@PathVariable Long id) {
		return ResponseEntity.ok(airplaneService.getAirplaneById(id));
	}

	/**
	 * Retrieves an airplane by its unique airplane number.
	 *
	 * @param airplaneNumber the airplane number
	 * @return the airplane response object
	 */
	@GetMapping("/number/{airplaneNumber}")
	public ResponseEntity<AirplaneResponse> getAirplaneByNumber(@PathVariable String airplaneNumber) {
		return ResponseEntity.ok(airplaneService.getAirplaneByNumber(airplaneNumber));
	}

	/**
	 * Updates an existing airplane using its ID. Only accessible by users with
	 * ADMIN role.
	 *
	 * @param id              the ID of the airplane to update
	 * @param airplaneRequest the updated airplane data
	 * @return the updated airplane response object
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<AirplaneResponse> updateAirplaneById(@PathVariable Long id,
			@Valid @RequestBody AirplaneRequest airplaneRequest) {
		return ResponseEntity.ok(airplaneService.updateAirplane(id, airplaneRequest));
	}

	/**
	 * Updates an existing airplane using its airplane number. Only accessible by
	 * users with ADMIN role.
	 *
	 * @param airplaneNumber the airplane number to update
	 * @param request        the updated airplane data
	 * @return the updated airplane response object
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/number/{airplaneNumber}")
	public ResponseEntity<AirplaneResponse> updateAirplaneByNumber(@PathVariable String airplaneNumber,
			@Valid @RequestBody AirplaneRequest request) {
		return ResponseEntity.ok(airplaneService.updateAirplane(airplaneNumber, request));
	}

	/**
	 * Deletes an airplane using its ID. Only accessible by users with ADMIN role.
	 *
	 * @param id the ID of the airplane to delete
	 * @return confirmation message
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAirplaneById(@PathVariable Long id) {
		airplaneService.deleteAirplane(id);
		return ResponseEntity.ok("Airplane deleted successfully");
	}

	/**
	 * Deletes an airplane using its airplane number. Only accessible by users with
	 * ADMIN role.
	 *
	 * @param airplaneNumber the airplane number to delete
	 * @return confirmation message
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/number/{airplaneNumber}")
	public ResponseEntity<String> deleteAirplaneByNumber(@PathVariable String airplaneNumber) {
		return ResponseEntity.ok(airplaneService.deleteAirplane(airplaneNumber));
	}

	/**
	 * Checks if an airplane exists by its number.
	 *
	 * @param airplaneNumber the airplane number to validate
	 * @return true if airplane exists, false otherwise
	 */
	@GetMapping("/exists/{airplaneNumber}")
	public ResponseEntity<Boolean> airplaneNumberExists(@PathVariable String airplaneNumber) {
		return ResponseEntity.ok(airplaneService.airplaneNumberExists(airplaneNumber));
	}

	/**
	 * Searches airplanes by a partial or full name keyword.
	 *
	 * @param keyword the keyword to search for
	 * @return list of matching airplanes
	 */
	@GetMapping("/search")
	public ResponseEntity<List<AirplaneResponse>> searchByName(@RequestParam String keyword) {
		return ResponseEntity.ok(airplaneService.searchByName(keyword));
	}

	/**
	 * Filters airplanes by their manufacturer.
	 *
	 * @param manufacturer the manufacturer name
	 * @return list of matching airplanes
	 */
	@GetMapping("/filter/manufacturer/{manufacturer}")
	public ResponseEntity<List<AirplaneResponse>> filterByManufacturer(@PathVariable String manufacturer) {
		return ResponseEntity.ok(airplaneService.filterByManufacturer(manufacturer));
	}

	/**
	 * Filters airplanes by their model.
	 *
	 * @param model the model name
	 * @return list of matching airplanes
	 */
	@GetMapping("/filter/model/{model}")
	public ResponseEntity<List<AirplaneResponse>> filterByModel(@PathVariable String model) {
		return ResponseEntity.ok(airplaneService.filterByModel(model));
	}

}
