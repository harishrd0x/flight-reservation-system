package com.version1.frs.model;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_FLIGHTS")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLIGHT_ID")
    private Long flightId;

    @Column(name = "FLIGHT_NUMBER", nullable = false, unique = true)
    private String flightNumber;

    @Column(name = "DEPARTURE_DATE", nullable = false)
    private LocalDate departureDate;

    @Column(name = "DEPARTURE_TIME", nullable = false)
    private LocalTime departureTime;

    @Column(name = "ARRIVAL_DATE", nullable = false)
    private LocalDate arrivalDate;

    @Column(name = "ARRIVAL_TIME", nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "AIRPLANE_ID", nullable = false)
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "SOURCE_AIRPORT_CODE", nullable = false)
    private Airport sourceAirport;

    @ManyToOne
    @JoinColumn(name = "DESTINATION_AIRPORT_CODE", nullable = false)
    private Airport destinationAirport;

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

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    // Helper method to get the source airport as "City (Code)"
    public String getSourceAirportCityCode() {
        return sourceAirport.getAirportCity() + " (" + sourceAirport.getAirportCode() + ")";
    }

    // Helper method to get the destination airport as "City (Code)"
    public String getDestinationAirportCityCode() {
        return destinationAirport.getAirportCity() + " (" + destinationAirport.getAirportCode() + ")";
    }
}
