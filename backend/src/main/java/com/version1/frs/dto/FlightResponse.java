package com.version1.frs.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightResponse {
    private Long flightId;
    private String flightNumber;
    private String sourceAirportCode;  // Updated to store the source airport code
    private String destinationAirportCode;  // Updated to store the destination airport code
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double price;
    private String airplaneName;

    // Getters and Setters
    public Long getFlightId() {
        return flightId;
    }
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSourceAirportCode() {
        return sourceAirportCode;
    }
    public void setSourceAirportCode(String sourceAirportCode) {
        this.sourceAirportCode = sourceAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }
    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getAirplaneName() {
        return airplaneName;
    }
    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }
}
