package com.version1.frs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_AIRPLANES")
public class Airplane {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AIRPLANE_ID")
	private Long airplaneId;

	@Column(name = "AIRPLANE_NAME", nullable = false)
	private String airplaneName;
	
	@Column(name = "AIRPLANE_NUMBER", nullable = false, unique = true)
	private String airplaneNumber;

	@Column(name = "AIRPLANE_MODEL", nullable = false)
	private String airplaneModel;

	@Column(name = "MANUFACTURER", nullable = false)
	private String manufacturer;

	@Column(name = "CAPACITY", nullable = false)
	private int capacity;

	// Getters and Setters
	public Long getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(Long airplaneId) {
		this.airplaneId = airplaneId;
	}

	public String getAirplaneName() {
		return airplaneName;
	}

	public void setAirplaneName(String airplaneName) {
		this.airplaneName = airplaneName;
	}

	
	public String getAirplaneNumber() {
		return airplaneNumber;
	}

	public void setAirplaneNumber(String airplaneNumber) {
		this.airplaneNumber = airplaneNumber;
	}

	public String getAirplaneModel() {
		return airplaneModel;
	}

	public void setAirplaneModel(String airplaneModel) {
		this.airplaneModel = airplaneModel;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}