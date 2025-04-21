package com.version1.frs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "TBL_FLIGHTS")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AIRPLANE_ID", nullable = false)
	private Airplane airplane;

	@Column(name = "DEPARTURE_TIME", nullable = false)
	private LocalDateTime departureTime;

	@Column(name = "ARRIVAL_TIME", nullable = false)
	private LocalDateTime arrivalTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FROM_AIRPORT_ID", nullable = false)
	private Airport fromAirport;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TO_AIRPORT_ID", nullable = false)
	private Airport toAirport;


	@Column(name = "PRICE", nullable = false)
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal price;

	@Column(name = "AIRLINE", nullable = false)
	private String airline; // Airline as a string (name)

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Airport getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(Airport fromAirport) {
		this.fromAirport = fromAirport;
	}

	public Airport getToAirport() {
		return toAirport;
	}

	public void setToAirport(Airport toAirport) {
		this.toAirport = toAirport;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
}
