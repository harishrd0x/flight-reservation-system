package com.version1.frs.service;

import java.util.List;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.dto.AirportResponse;
import com.version1.frs.model.Airport;

/**
 * Service interface for managing airports in the system. It includes methods
 * for CRUD operations, validation, and searching/filtering airports.
 */
public interface AirportService {

	// -------------------- Create --------------------

	/**
	 * Adds a new airport to the system.
	 * 
	 * @param request the {@link AirportRequest} containing the details of the
	 *                airport to add
	 * @return a message indicating the result of the operation
	 */
	String addAirport(AirportRequest request);

	// -------------------- Update --------------------

	/**
	 * Updates an existing airport's details using the airport code.
	 * 
	 * @param airportCode the code of the airport to update
	 * @param request     the {@link AirportRequest} containing the updated airport
	 *                    details
	 * @return a message indicating the result of the operation
	 */
	String updateAirport(String airportCode, AirportRequest request);

	// -------------------- Delete --------------------

	/**
	 * Deletes an airport from the system using the airport code.
	 * 
	 * @param airportCode the code of the airport to delete
	 * @return a message indicating the result of the operation
	 */
	String deleteAirport(String airportCode);

	// -------------------- Read --------------------

	/**
	 * Retrieves all airports in the system.
	 * 
	 * @return a list of {@link AirportResponse} DTOs representing all airports
	 */
	List<AirportResponse> getAllAirports();

	/**
	 * Retrieves an airport by its code.
	 * 
	 * @param airportCode the code of the airport to retrieve
	 * @return the {@link AirportResponse} DTO representing the airport
	 * @throws RuntimeException if no airport with the specified code is found
	 */
	AirportResponse getAirportByCode(String airportCode);

	/**
	 * Retrieves airports located in the specified city.
	 * 
	 * @param city the city to filter airports by
	 * @return a list of {@link AirportResponse} DTOs representing airports in the
	 *         specified city
	 */
	List<AirportResponse> getAirportsByCity(String city);

	/**
	 * Retrieves airports located in the specified state.
	 * 
	 * @param state the state to filter airports by
	 * @return a list of {@link AirportResponse} DTOs representing airports in the
	 *         specified state
	 */
	List<AirportResponse> getAirportsByState(String state);

	/**
	 * Retrieves airports located in the specified country.
	 * 
	 * @param country the country to filter airports by
	 * @return a list of {@link AirportResponse} DTOs representing airports in the
	 *         specified country
	 */
	List<AirportResponse> getAirportsByCountry(String country);

	// -------------------- Validation --------------------

	/**
	 * Checks if an airport exists in the system using its airport code.
	 * 
	 * @param airportCode the code of the airport to check
	 * @return true if the airport exists, false otherwise
	 */
	boolean airportExists(String airportCode);

	// -------------------- Filters --------------------

	/**
	 * Filters airports by state.
	 * 
	 * @param state the state to filter airports by
	 * @return a list of {@link AirportResponse} DTOs representing airports in the
	 *         specified state
	 */
	List<AirportResponse> filterByState(String state);

	/**
	 * Filters airports by city.
	 * 
	 * @param city the city to filter airports by
	 * @return a list of {@link AirportResponse} DTOs representing airports in the
	 *         specified city
	 */
	List<AirportResponse> filterByCity(String city);

	/**
	 * Filters airports by country.
	 * 
	 * @param country the country to filter airports by
	 * @return a list of {@link AirportResponse} DTOs representing airports in the
	 *         specified country
	 */
	List<AirportResponse> filterByCountry(String country);

	/**
	 * Filters airports by state.
	 * 
	 * @param state the state to filter airports by
	 * @return a list of {@link AirportResponse} DTOs representing airports in the
	 *         specified state
	 */
	List<AirportResponse> filterAirportsByState(String state);

	/**
	 * Filters airports by country.
	 * 
	 * @param country the country to filter airports by
	 * @return a list of {@link AirportResponse} DTOs representing airports in the
	 *         specified country
	 */
	List<AirportResponse> filterAirportsByCountry(String country);

	// -------------------- Search --------------------

	/**
	 * Searches for airports based on a query string. The search can match airport
	 * code, name, or other attributes.
	 * 
	 * @param query the search query to filter airports by
	 * @return a list of {@link Airport} entities matching the search criteria
	 */
	List<Airport> searchAirports(String query);
}
