package com.version1.frs.dto;

public class AirportResponse {

    private Long airportId; // surrogate key
    private String airportCode;
    private String airportName;
    private String airportCity;
    private String airportState;
    private String airportCountry;

    // Getters and Setters
   
    public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

	public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
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
