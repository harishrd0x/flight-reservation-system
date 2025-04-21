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

/**
 * Entity class representing a Flight in the system. Maps to the TBL_FLIGHTS
 * table in the database and includes details like departure/arrival times,
 * price, airline name, associated airports, and airplane.
 */
@Entity
@Table(name = "TBL_FLIGHTS")
public class Flight {

	// -------------------- Fields --------------------

	/**
	 * Unique identifier for the flight. Mapped to the 'ID' column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/**
	 * The airplane assigned to the flight. Represents a many-to-one relationship
	 * with {@link Airplane}.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AIRPLANE_ID", nullable = false)
	private Airplane airplane;

	/**
	 * The scheduled departure time of the flight. Mapped to the 'DEPARTURE_TIME'
	 * column in the database.
	 */
	@Column(name = "DEPARTURE_TIME", nullable = false)
	private LocalDateTime departureTime;

	/**
	 * The scheduled arrival time of the flight. Mapped to the 'ARRIVAL_TIME' column
	 * in the database.
	 */
	@Column(name = "ARRIVAL_TIME", nullable = false)
	private LocalDateTime arrivalTime;

	/**
	 * The airport from which the flight departs. Represents a many-to-one
	 * relationship with {@link Airport}.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FROM_AIRPORT_ID", nullable = false)
	private Airport fromAirport;

	/**
	 * The airport at which the flight arrives. Represents a many-to-one
	 * relationship with {@link Airport}.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TO_AIRPORT_ID", nullable = false)
	private Airport toAirport;

	/**
	 * The price of the flight ticket. Mapped to the 'PRICE' column in the database.
	 * Must be a positive value.
	 */
	@Column(name = "PRICE", nullable = false)
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal price;

	/**
	 * The name of the airline operating the flight. Mapped to the 'AIRLINE' column
	 * in the database.
	 */
	@Column(name = "AIRLINE", nullable = false)
	private String airline;

	// -------------------- Getters and Setters --------------------

	/**
	 * Gets the flight ID.
	 * 
	 * @return the flight ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the flight ID.
	 * 
	 * @param id the flight ID to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the airplane assigned to this flight.
	 * 
	 * @return the airplane
	 */
	public Airplane getAirplane() {
		return airplane;
	}

	/**
	 * Sets the airplane assigned to this flight.
	 * 
	 * @param airplane the airplane to set
	 */
	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	/**
	 * Gets the departure time of the flight.
	 * 
	 * @return the departure time
	 */
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	/**
	 * Sets the departure time of the flight.
	 * 
	 * @param departureTime the departure time to set
	 */
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * Gets the arrival time of the flight.
	 * 
	 * @return the arrival time
	 */
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the arrival time of the flight.
	 * 
	 * @param arrivalTime the arrival time to set
	 */
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Gets the airport the flight is departing from.
	 * 
	 * @return the departure airport
	 */
	public Airport getFromAirport() {
		return fromAirport;
	}

	/**
	 * Sets the airport the flight is departing from.
	 * 
	 * @param fromAirport the departure airport to set
	 */
	public void setFromAirport(Airport fromAirport) {
		this.fromAirport = fromAirport;
	}

	/**
	 * Gets the airport the flight is arriving at.
	 * 
	 * @return the arrival airport
	 */
	public Airport getToAirport() {
		return toAirport;
	}

	/**
	 * Sets the airport the flight is arriving at.
	 * 
	 * @param toAirport the arrival airport to set
	 */
	public void setToAirport(Airport toAirport) {
		this.toAirport = toAirport;
	}

	/**
	 * Gets the price of the flight.
	 * 
	 * @return the flight price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price of the flight.
	 * 
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the airline name operating the flight.
	 * 
	 * @return the airline name
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Sets the airline name operating the flight.
	 * 
	 * @param airline the airline name to set
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}
}
