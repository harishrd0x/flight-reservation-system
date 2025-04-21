package com.version1.frs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing an Airport in the system. This class maps to the
 * 'TBL_AIRPORTS' table in the database.
 */
@Entity
@Table(name = "TBL_AIRPORTS")
public class Airport {

	// -------------------- Fields --------------------

	/**
	 * Unique identifier for the airport. Mapped to the 'ID' column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/**
	 * Unique airport code (e.g., IATA code). Mapped to the 'AIRPORT_CODE' column in
	 * the database. This value must be unique for each airport.
	 */
	@Column(name = "AIRPORT_CODE", nullable = false, unique = true, length = 10)
	private String airportCode;

	/**
	 * Name of the airport. Mapped to the 'AIRPORT_NAME' column in the database.
	 */
	@Column(name = "AIRPORT_NAME", nullable = false, length = 100)
	private String airportName;

	/**
	 * City where the airport is located. Mapped to the 'AIRPORT_CITY' column in the
	 * database.
	 */
	@Column(name = "AIRPORT_CITY", nullable = false, length = 100)
	private String airportCity;

	/**
	 * State where the airport is located. Mapped to the 'AIRPORT_STATE' column in
	 * the database.
	 */
	@Column(name = "AIRPORT_STATE", nullable = false, length = 100)
	private String airportState;

	/**
	 * Country where the airport is located. Mapped to the 'AIRPORT_COUNTRY' column
	 * in the database.
	 */
	@Column(name = "AIRPORT_COUNTRY", nullable = false, length = 100)
	private String airportCountry;

	// -------------------- Getters and Setters --------------------

	/**
	 * Gets the unique identifier of the airport.
	 * 
	 * @return the airport ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique identifier for the airport.
	 * 
	 * @param id the airport ID to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the airport code (e.g., IATA code).
	 * 
	 * @return the airport code
	 */
	public String getAirportCode() {
		return airportCode;
	}

	/**
	 * Sets the airport code (e.g., IATA code).
	 * 
	 * @param airportCode the airport code to set
	 */
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	/**
	 * Gets the name of the airport.
	 * 
	 * @return the airport name
	 */
	public String getAirportName() {
		return airportName;
	}

	/**
	 * Sets the name of the airport.
	 * 
	 * @param airportName the airport name to set
	 */
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	/**
	 * Gets the city where the airport is located.
	 * 
	 * @return the airport city
	 */
	public String getAirportCity() {
		return airportCity;
	}

	/**
	 * Sets the city where the airport is located.
	 * 
	 * @param airportCity the airport city to set
	 */
	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}

	/**
	 * Gets the state where the airport is located.
	 * 
	 * @return the airport state
	 */
	public String getAirportState() {
		return airportState;
	}

	/**
	 * Sets the state where the airport is located.
	 * 
	 * @param airportState the airport state to set
	 */
	public void setAirportState(String airportState) {
		this.airportState = airportState;
	}

	/**
	 * Gets the country where the airport is located.
	 * 
	 * @return the airport country
	 */
	public String getAirportCountry() {
		return airportCountry;
	}

	/**
	 * Sets the country where the airport is located.
	 * 
	 * @param airportCountry the airport country to set
	 */
	public void setAirportCountry(String airportCountry) {
		this.airportCountry = airportCountry;
	}
}
