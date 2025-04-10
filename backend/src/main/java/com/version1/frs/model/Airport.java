package com.version1.frs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private int airportId;

    @NotBlank(message = "Airport name is required")
    @Size(max = 100, message = "Airport name must be at most 100 characters")
    @Column(name = "airport_name", nullable = false, length = 100)
    private String airportName;

    @NotBlank(message = "Airport code is required")
    @Size(max = 10, message = "Airport code must be at most 10 characters")
    @Column(name = "airport_code", nullable = false, unique = true, length = 10)
    private String airportCode;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City name must be at most 100 characters")
    @Column(name = "airport_city", nullable = false, length = 100)
    private String airportCity;

    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State must be at most 100 characters")
    @Column(name = "airport_state", nullable = false, length = 100)
    private String airportState;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country must be at most 100 characters")
    @Column(name = "airport_country", nullable = false, length = 100)
    private String airportCountry;

    // --- Getters and Setters ---

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
