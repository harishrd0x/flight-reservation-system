package com.version1.frs.dto;

import java.math.BigDecimal;

public class FlightResponse {

    private Long id;
    private String airline;
    private String departureTime;
    private String arrivalTime;
    private Long fromAirportId;
    private String fromAirportName;
    private Long toAirportId;
    private String toAirportName;
    private BigDecimal price;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getFromAirportId() {
        return fromAirportId;
    }

    public void setFromAirportId(Long fromAirportId) {
        this.fromAirportId = fromAirportId;
    }

    public String getFromAirportName() {
        return fromAirportName;
    }

    public void setFromAirportName(String fromAirportName) {
        this.fromAirportName = fromAirportName;
    }

    public Long getToAirportId() {
        return toAirportId;
    }

    public void setToAirportId(Long toAirportId) {
        this.toAirportId = toAirportId;
    }

    public String getToAirportName() {
        return toAirportName;
    }

    public void setToAirportName(String toAirportName) {
        this.toAirportName = toAirportName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
