package com.version1.frs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.version1.frs.model.Airport;

/**
 * Repository interface for managing {@link Airport} entities. Provides CRUD
 * operations and custom query methods for airport-related data.
 */
public interface AirportRepository extends JpaRepository<Airport, Long> {

	/**
	 * Finds an airport by its unique airport code.
	 *
	 * @param airportCode the unique airport code
	 * @return an Optional containing the found Airport, or empty if not found
	 */
	Optional<Airport> findByAirportCode(String airportCode);

	/**
	 * Retrieves a list of airports by matching city (case-insensitive).
	 *
	 * @param city the city name to match
	 * @return a list of airports located in the specified city
	 */
	List<Airport> findByAirportCityIgnoreCase(String city);

	/**
	 * Retrieves a list of airports by matching state (case-insensitive).
	 *
	 * @param state the state name to match
	 * @return a list of airports located in the specified state
	 */
	List<Airport> findByAirportStateIgnoreCase(String state);

	/**
	 * Retrieves a list of airports by matching country (case-insensitive).
	 *
	 * @param country the country name to match
	 * @return a list of airports located in the specified country
	 */
	List<Airport> findByAirportCountryIgnoreCase(String country);

	/**
	 * Searches airports by keyword matching airport name, city, country, or code
	 * (case-insensitive).
	 *
	 * @param keyword the keyword to search for
	 * @return a list of airports that match the keyword in name, city, country, or
	 *         code
	 */
	@Query("SELECT a FROM Airport a WHERE " + "LOWER(a.airportName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(a.airportCity) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(a.airportCountry) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(a.airportCode) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Airport> searchAirports(@Param("keyword") String keyword);
}
