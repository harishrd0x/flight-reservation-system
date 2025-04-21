package com.version1.frs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.version1.frs.model.Airplane;

/**
 * Repository interface for managing {@link Airplane} entities. Provides CRUD
 * operations and custom query methods for accessing airplane data.
 */
@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

	/**
	 * Finds an airplane by its unique airplane number.
	 *
	 * @param airplaneNumber the unique identifier of the airplane
	 * @return an Optional containing the found Airplane, or empty if not found
	 */
	Optional<Airplane> findByAirplaneNumber(String airplaneNumber);

	/**
	 * Checks if an airplane with the specified number exists.
	 *
	 * @param airplaneNumber the airplane number to check
	 * @return true if an airplane exists with the given number, false otherwise
	 */
	boolean existsByAirplaneNumber(String airplaneNumber);

	/**
	 * Searches airplanes whose names contain the given keyword, case-insensitive.
	 *
	 * @param keyword the keyword to search for in airplane names
	 * @return a list of airplanes matching the criteria
	 */
	List<Airplane> findByAirplaneNameContainingIgnoreCase(String keyword);

	/**
	 * Finds all airplanes by the specified manufacturer, ignoring case.
	 *
	 * @param manufacturer the manufacturer name
	 * @return a list of airplanes by the given manufacturer
	 */
	List<Airplane> findByManufacturerIgnoreCase(String manufacturer);

	/**
	 * Finds all airplanes by the specified model, ignoring case.
	 *
	 * @param model the model of the airplane
	 * @return a list of airplanes with the given model
	 */
	List<Airplane> findByAirplaneModelIgnoreCase(String model);

	/**
	 * Finds all airplanes with capacity greater than or equal to the specified
	 * value.
	 *
	 * @param capacity the minimum capacity
	 * @return a list of airplanes with at least the given capacity
	 */
	List<Airplane> findByCapacityGreaterThanEqual(int capacity);

	/**
	 * Finds all airplanes with capacity between the specified minimum and maximum
	 * values.
	 *
	 * @param min the minimum capacity
	 * @param max the maximum capacity
	 * @return a list of airplanes with capacity in the specified range
	 */
	List<Airplane> findByCapacityBetween(int min, int max);
}
