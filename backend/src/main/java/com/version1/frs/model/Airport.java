package com.version1.frs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TBL_AIRPORTS")
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AIRPORT_ID")
	private int airportId;

	@NotBlank
	@Size(max = 100)
	@Column(name = "AIRPORT_NAME", nullable = false, length = 100)
	private String airportName;

	@NotBlank
	@Size(max = 10)
	@Column(name = "AIRPORT_CODE", nullable = false, unique = true, length = 10)
	private String airportCode;

	@NotBlank
	@Size(max = 100)
	@Column(name = "AIRPORT_CITY", nullable = false, length = 100)
	private String airportCity;

	@NotBlank
	@Size(max = 100)
	@Column(name = "AIRPORT_STATE", nullable = false, length = 100)
	private String airportState;

	@NotBlank
	@Size(max = 100)
	@Column(name = "AIRPORT_COUNTRY", nullable = false, length = 100)
	private String airportCountry;

	// Getters and Setters
	public int getAirportId() {
		return airportId;
	}

	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportCity() {
		return airportCity;
	}

	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}

	public String getAirportState() {
		return airportState;
	}

	public void setAirportState(String airportState) {
		this.airportState = airportState;
	}

	public String getAirportCountry() {
		return airportCountry;
	}

	public void setAirportCountry(String airportCountry) {
		this.airportCountry = airportCountry;
	}
}
