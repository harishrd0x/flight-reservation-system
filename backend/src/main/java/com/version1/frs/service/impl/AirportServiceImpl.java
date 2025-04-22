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
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.dto.AirportResponse;
import com.version1.frs.model.Airport;
import com.version1.frs.repository.AirportRepository;
import com.version1.frs.service.AirportService;

/**
 * Implementation of the {@link AirportService} interface. Provides business
 * logic and operations for airport management, including CRUD operations,
 * search and filter functionalities.
 */
@Service
public class AirportServiceImpl implements AirportService {

	private final AirportRepository airportRepository;

	/**
	 * Constructor-based injection for {@link AirportRepository}.
	 *
	 * @param airportRepository the repository used to manage airport data
	 */
	public AirportServiceImpl(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
	}

	/**
	 * Adds a new airport if the code does not already exist.
	 *
	 * @param request the airport details
	 * @return confirmation message
	 * @throws RuntimeException if airport with the same code already exists
	 */
	@Override
	public String addAirport(AirportRequest request) {
		if (airportRepository.findByAirportCode(request.getAirportCode()).isPresent()) {
			throw new RuntimeException("Airport already exists with code: " + request.getAirportCode());
		}
		airportRepository.save(mapToEntity(request));
		return "Airport added successfully.";
	}

	/**
	 * Updates airport details for a given airport code.
	 *
	 * @param airportCode the unique code of the airport
	 * @param request     the updated airport details
	 * @return confirmation message
	 * @throws RuntimeException if airport not found
	 */
	@Override
	public String updateAirport(String airportCode, AirportRequest request) {
		Optional<Airport> existing = airportRepository.findByAirportCode(airportCode);
		if (existing.isEmpty()) {
			throw new RuntimeException("Airport not found with code: " + airportCode);
		}
		Airport airport = mapToEntity(request);
		airport.setId(existing.get().getId());
		airportRepository.save(airport);
		return "Airport updated successfully.";
	}

	/**
	 * Deletes an airport based on airport code.
	 *
	 * @param airportCode the unique airport code
	 * @return confirmation message
	 * @throws RuntimeException if airport not found
	 */
	@Override
	public String deleteAirport(String airportCode) {
		Optional<Airport> airport = airportRepository.findByAirportCode(airportCode);
		if (airport.isEmpty()) {
			throw new RuntimeException("Airport not found with code: " + airportCode);
		}
		airportRepository.deleteById(airport.get().getId());
		return "Airport deleted successfully.";
	}

	/**
	 * Fetches all airports.
	 *
	 * @return list of airport response DTOs
	 */
	@Override
	public List<AirportResponse> getAllAirports() {
		return airportRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	/**
	 * Retrieves airport details by its unique code.
	 *
	 * @param airportCode the airport code
	 * @return airport response DTO
	 * @throws RuntimeException if airport not found
	 */
	@Override
	public AirportResponse getAirportByCode(String airportCode) {
		Airport airport = airportRepository.findByAirportCode(airportCode)
				.orElseThrow(() -> new RuntimeException("Airport not found with code: " + airportCode));
		return mapToResponse(airport);
	}

	/**
	 * Searches for airports based on a query string (custom implementation).
	 *
	 * @param query the search string
	 * @return list of matching airports
	 */
	public List<Airport> searchAirports(String query) {
		return airportRepository.searchAirports(query);
	}

	/**
	 * Filters airports by city.
	 *
	 * @param city the city name
	 * @return list of airport responses matching the city
	 */
	@Override
	public List<AirportResponse> filterByCity(String city) {
		return airportRepository.findByAirportCityIgnoreCase(city).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Filters airports by state.
	 *
	 * @param state the state name
	 * @return list of airport responses matching the state
	 */
	@Override
	public List<AirportResponse> filterByState(String state) {
		return airportRepository.findByAirportStateIgnoreCase(state).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Filters airports by country.
	 *
	 * @param country the country name
	 * @return list of airport responses matching the country
	 */
	@Override
	public List<AirportResponse> filterByCountry(String country) {
		return airportRepository.findByAirportCountryIgnoreCase(country).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Gets airports located in a specific city.
	 *
	 * @param city the city name
	 * @return list of airport responses
	 */
	@Override
	public List<AirportResponse> getAirportsByCity(String city) {
		return airportRepository.findByAirportCityIgnoreCase(city).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Gets airports located in a specific state.
	 *
	 * @param state the state name
	 * @return list of airport responses
	 */
	@Override
	public List<AirportResponse> getAirportsByState(String state) {
		return airportRepository.findByAirportStateIgnoreCase(state).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Gets airports located in a specific country.
	 *
	 * @param country the country name
	 * @return list of airport responses
	 */
	@Override
	public List<AirportResponse> getAirportsByCountry(String country) {
		return airportRepository.findByAirportCountryIgnoreCase(country).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Checks if an airport exists based on its code.
	 *
	 * @param airportCode the airport code
	 * @return true if airport exists, false otherwise
	 */
	@Override
	public boolean airportExists(String airportCode) {
		return airportRepository.findByAirportCode(airportCode).isPresent();
	}

	/**
	 * Filters airports by state (duplicate of getAirportsByState).
	 *
	 * @param state the state name
	 * @return list of airport responses
	 */
	@Override
	public List<AirportResponse> filterAirportsByState(String state) {
		return airportRepository.findByAirportStateIgnoreCase(state).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Filters airports by country (duplicate of getAirportsByCountry).
	 *
	 * @param country the country name
	 * @return list of airport responses
	 */
	@Override
	public List<AirportResponse> filterAirportsByCountry(String country) {
		return airportRepository.findByAirportCountryIgnoreCase(country).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Maps an Airport entity to a response DTO.
	 *
	 * @param airport the airport entity
	 * @return mapped airport response
	 */
	private AirportResponse mapToResponse(Airport airport) {
		AirportResponse response = new AirportResponse();
		response.setAirportId(airport.getId());
		response.setAirportCode(airport.getAirportCode());
		response.setAirportName(airport.getAirportName());
		response.setAirportCity(airport.getAirportCity());
		response.setAirportState(airport.getAirportState());
		response.setAirportCountry(airport.getAirportCountry());
		return response;
	}

	/**
	 * Maps a request DTO to an Airport entity.
	 *
	 * @param request the airport request DTO
	 * @return populated airport entity
	 */
	private Airport mapToEntity(AirportRequest request) {
		Airport airport = new Airport();
		airport.setAirportCode(request.getAirportCode());
		airport.setAirportName(request.getAirportName());
		airport.setAirportCity(request.getAirportCity());
		airport.setAirportState(request.getAirportState());
		airport.setAirportCountry(request.getAirportCountry());
		return airport;
	}
}
