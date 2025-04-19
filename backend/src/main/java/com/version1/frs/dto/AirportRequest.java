package com.version1.frs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AirportRequest {

    @NotBlank(message = "Airport code cannot be blank")
    @Size(min = 3, max = 10, message = "Airport code must be between 3 and 10 characters")
    private String airportCode;

    @NotBlank(message = "Airport name cannot be blank")
    @Size(min = 2, max = 100, message = "Airport name must be between 2 and 100 characters")
    private String airportName;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    private String airportCity;

    @NotBlank(message = "State cannot be blank")
    @Size(min = 2, max = 100, message = "State must be between 2 and 100 characters")
    private String airportState;

    @NotBlank(message = "Country cannot be blank")
    @Size(min = 2, max = 100, message = "Country must be between 2 and 100 characters")
    private String airportCountry;

    // Getters and Setters
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
