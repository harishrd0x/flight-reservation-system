package com.version1.frs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_AIRPORTS")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AIRPORT_CODE", nullable = false, unique = true, length = 10)
    private String airportCode;

    @Column(name = "AIRPORT_NAME", nullable = false, length = 100)
    private String airportName;

    @Column(name = "AIRPORT_CITY", nullable = false, length = 100)
    private String airportCity;

    @Column(name = "AIRPORT_STATE", nullable = false, length = 100)
    private String airportState;

    @Column(name = "AIRPORT_COUNTRY", nullable = false, length = 100)
    private String airportCountry;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
