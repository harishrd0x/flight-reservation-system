package com.version1.frs.service;

import java.time.LocalDate;
import java.util.List;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.dto.FlightResponse;

/**
 * Service interface for managing flights in the system. It includes methods for
 * adding, retrieving, deleting flights, and searching for flights.
 */
public interface FlightService {

	// -------------------- Create --------------------

	/**
	 * Adds a new flight to the system.
	 * 
	 * @param flightRequest the {@link FlightRequest} DTO containing the details of
	 *                      the flight
	 * @return the {@link FlightResponse} DTO containing the added flight's details
	 */
	FlightResponse addFlight(FlightRequest flightRequest);

	// -------------------- Read --------------------

	/**
	 * Retrieves all flights in the system.
	 * 
	 * @return a list of {@link FlightResponse} DTOs representing all flights
	 */
	List<FlightResponse> getAllFlights();

	/**
	 * Retrieves a specific flight by its ID.
	 * 
	 * @param id the ID of the flight to retrieve
	 * @return the {@link FlightResponse} DTO representing the flight
	 * @throws RuntimeException if no flight with the specified ID is found
	 */
	FlightResponse getFlightById(Long id);

	// -------------------- Delete --------------------

	/**
	 * Deletes a flight from the system by its ID.
	 * 
	 * @param id the ID of the flight to delete
	 * @throws RuntimeException if no flight with the specified ID is found
	 */
	void deleteFlight(Long id);

	// -------------------- Search --------------------

	/**
	 * Searches for flights based on source and destination airports and the desired
	 * date.
	 * 
	 * @param sourceId      the ID of the source airport
	 * @param destinationId the ID of the destination airport
	 * @param date          the date for which flights are being searched
	 * @return a list of {@link FlightResponse} DTOs representing flights that match
	 *         the search criteria
	 */
	List<FlightResponse> searchFlights(Long sourceId, Long destinationId, LocalDate date);
}
