package com.version1.frs.service;

import java.util.List;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.dto.AirplaneResponse;

/**
 * Service interface for managing airplanes in the system. It includes methods
 * for CRUD operations, validation, and searching/filtering airplanes.
 */
public interface AirplaneService {

	// -------------------- Create --------------------

	/**
	 * Adds a new airplane to the system.
	 * 
	 * @param request the {@link AirplaneRequest} containing the airplane details
	 * @return the {@link AirplaneResponse} DTO representing the added airplane
	 */
	AirplaneResponse addAirplane(AirplaneRequest request);

	// -------------------- Read --------------------

	/**
	 * Retrieves all airplanes in the system.
	 * 
	 * @return a list of {@link AirplaneResponse} DTOs representing all airplanes
	 */
	List<AirplaneResponse> getAllAirplanes();

	/**
	 * Retrieves an airplane by its ID.
	 * 
	 * @param id the ID of the airplane
	 * @return the {@link AirplaneResponse} DTO representing the airplane
	 * @throws RuntimeException if no airplane with the specified ID is found
	 */
	AirplaneResponse getAirplaneById(Long id);

	/**
	 * Retrieves an airplane by its airplane number.
	 * 
	 * @param airplaneNumber the number of the airplane
	 * @return the {@link AirplaneResponse} DTO representing the airplane
	 * @throws RuntimeException if no airplane with the specified number is found
	 */
	AirplaneResponse getAirplaneByNumber(String airplaneNumber);

	// -------------------- Update --------------------

	/**
	 * Updates an existing airplane's details using its ID.
	 * 
	 * @param id      the ID of the airplane to update
	 * @param request the {@link AirplaneRequest} containing the updated airplane
	 *                details
	 * @return the updated {@link AirplaneResponse} DTO
	 * @throws RuntimeException if no airplane with the specified ID is found
	 */
	AirplaneResponse updateAirplane(Long id, AirplaneRequest request); // Using ID

	/**
	 * Updates an existing airplane's details using its airplane number.
	 * 
	 * @param airplaneNumber the number of the airplane to update
	 * @param request        the {@link AirplaneRequest} containing the updated
	 *                       airplane details
	 * @return the updated {@link AirplaneResponse} DTO
	 * @throws RuntimeException if no airplane with the specified number is found
	 */
	AirplaneResponse updateAirplane(String airplaneNumber, AirplaneRequest request); // Using airplaneNumber

	// -------------------- Delete --------------------

	/**
	 * Deletes an airplane from the system using its ID.
	 * 
	 * @param id the ID of the airplane to delete
	 * @return a message indicating whether the deletion was successful
	 * @throws RuntimeException if no airplane with the specified ID is found
	 */
	String deleteAirplane(Long id); // Using ID

	/**
	 * Deletes an airplane from the system using its airplane number.
	 * 
	 * @param airplaneNumber the number of the airplane to delete
	 * @return a message indicating whether the deletion was successful
	 * @throws RuntimeException if no airplane with the specified number is found
	 */
	String deleteAirplane(String airplaneNumber); // Using airplaneNumber

	// -------------------- Validation --------------------

	/**
	 * Checks if an airplane number already exists in the system.
	 * 
	 * @param airplaneNumber the number of the airplane
	 * @return true if the airplane number exists, false otherwise
	 */
	boolean airplaneNumberExists(String airplaneNumber);

	// -------------------- Search / Filters --------------------

	/**
	 * Searches for airplanes by their name.
	 * 
	 * @param keyword the search keyword for the airplane name
	 * @return a list of {@link AirplaneResponse} DTOs representing the airplanes
	 *         that match the search
	 */
	List<AirplaneResponse> searchByName(String keyword);

	/**
	 * Filters airplanes by their manufacturer.
	 * 
	 * @param manufacturer the manufacturer name to filter by
	 * @return a list of {@link AirplaneResponse} DTOs representing the airplanes
	 *         from the specified manufacturer
	 */
	List<AirplaneResponse> filterByManufacturer(String manufacturer);

	/**
	 * Filters airplanes by their model.
	 * 
	 * @param model the model name to filter by
	 * @return a list of {@link AirplaneResponse} DTOs representing the airplanes of
	 *         the specified model
	 */
	List<AirplaneResponse> filterByModel(String model);

	/**
	 * Filters airplanes by their capacity greater than or equal to the specified
	 * value.
	 * 
	 * @param minCapacity the minimum capacity to filter by
	 * @return a list of {@link AirplaneResponse} DTOs representing the airplanes
	 *         with a capacity greater than or equal to the specified value
	 */
	List<AirplaneResponse> findByCapacityGreaterThanEqual(int minCapacity);

	/**
	 * Filters airplanes by their capacity within a specified range.
	 * 
	 * @param min the minimum capacity of the range
	 * @param max the maximum capacity of the range
	 * @return a list of {@link AirplaneResponse} DTOs representing the airplanes
	 *         within the specified capacity range
	 */
	List<AirplaneResponse> findByCapacityBetween(int min, int max);
}
