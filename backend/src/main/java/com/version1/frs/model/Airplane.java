package com.version1.frs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing an Airplane in the system. This class maps to the
 * 'TBL_AIRPLANES' table in the database.
 */
@Entity
@Table(name = "TBL_AIRPLANES")
public class Airplane {

	// -------------------- Fields --------------------

	/**
	 * Unique identifier for the airplane. Mapped to the 'AIRPLANE_ID' column in the
	 * database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AIRPLANE_ID")
	private Long airplaneId;

	/**
	 * Name of the airplane. Mapped to the 'AIRPLANE_NAME' column in the database.
	 */
	@Column(name = "AIRPLANE_NAME", nullable = false)
	private String airplaneName;

	/**
	 * Unique identifier for the airplane (e.g., registration number). Mapped to the
	 * 'AIRPLANE_NUMBER' column in the database. This value must be unique for each
	 * airplane.
	 */
	@Column(name = "AIRPLANE_NUMBER", nullable = false, unique = true)
	private String airplaneNumber;

	/**
	 * Model of the airplane. Mapped to the 'AIRPLANE_MODEL' column in the database.
	 */
	@Column(name = "AIRPLANE_MODEL", nullable = false)
	private String airplaneModel;

	/**
	 * Manufacturer of the airplane. Mapped to the 'MANUFACTURER' column in the
	 * database.
	 */
	@Column(name = "MANUFACTURER", nullable = false)
	private String manufacturer;

	/**
	 * Capacity of the airplane (number of passengers it can carry). Mapped to the
	 * 'CAPACITY' column in the database.
	 */
	@Column(name = "CAPACITY", nullable = false)
	private int capacity;

	// -------------------- Getters and Setters --------------------

	/**
	 * Gets the unique identifier of the airplane.
	 * 
	 * @return the airplane ID
	 */
	public Long getAirplaneId() {
		return airplaneId;
	}

	/**
	 * Sets the unique identifier for the airplane.
	 * 
	 * @param airplaneId the airplane ID to set
	 */
	public void setAirplaneId(Long airplaneId) {
		this.airplaneId = airplaneId;
	}

	/**
	 * Gets the name of the airplane.
	 * 
	 * @return the airplane name
	 */
	public String getAirplaneName() {
		return airplaneName;
	}

	/**
	 * Sets the name of the airplane.
	 * 
	 * @param airplaneName the airplane name to set
	 */
	public void setAirplaneName(String airplaneName) {
		this.airplaneName = airplaneName;
	}

	/**
	 * Gets the unique identifier (number) of the airplane.
	 * 
	 * @return the airplane number
	 */
	public String getAirplaneNumber() {
		return airplaneNumber;
	}

	/**
	 * Sets the unique identifier (number) for the airplane.
	 * 
	 * @param airplaneNumber the airplane number to set
	 */
	public void setAirplaneNumber(String airplaneNumber) {
		this.airplaneNumber = airplaneNumber;
	}

	/**
	 * Gets the model of the airplane.
	 * 
	 * @return the airplane model
	 */
	public String getAirplaneModel() {
		return airplaneModel;
	}

	/**
	 * Sets the model of the airplane.
	 * 
	 * @param airplaneModel the airplane model to set
	 */
	public void setAirplaneModel(String airplaneModel) {
		this.airplaneModel = airplaneModel;
	}

	/**
	 * Gets the manufacturer of the airplane.
	 * 
	 * @return the manufacturer of the airplane
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the manufacturer of the airplane.
	 * 
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Gets the capacity of the airplane.
	 * 
	 * @return the capacity (number of passengers)
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity of the airplane.
	 * 
	 * @param capacity the capacity to set (number of passengers)
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
