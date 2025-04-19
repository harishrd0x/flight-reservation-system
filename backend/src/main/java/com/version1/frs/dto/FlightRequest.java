package com.version1.frs.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.*;

public class FlightRequest {

    @NotBlank
    private String flightNumber;

    @NotNull
    private LocalDate departureDate;

    @NotNull
    private LocalTime departureTime;

    @NotNull
    private LocalDate arrivalDate;

    @NotNull
    private LocalTime arrivalTime;

    @NotNull
    @Positive
    private Double price;

    @NotBlank
    private String airplaneNumber;

    @NotBlank
    private String sourceAirportCode;

    @NotBlank
    private String destinationAirportCode;

    // Getters and Setters
    
	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
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

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAirplaneNumber() {
		return airplaneNumber;
	}

	public void setAirplaneNumber(String airplaneNumber) {
		this.airplaneNumber = airplaneNumber;
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

}
