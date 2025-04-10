package com.version1.frs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "tbl_bookings")

public class Bookings {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long bookingId;
	@Column(name = "flight_id")
	private Long flightId;
	@Column(name = "passenger_name")
	private String passengerName;
	@Column(name = "email")
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "seat_number")
	private String seatNumber;
	@Column(name = "status")
	private String status;

	// Getters and Setters

	public Long getBookingId() {

		return bookingId;

	}

	public void setBookingId(Long bookingId) {

		this.bookingId = bookingId;

	}

	public Long getFlightId() {

		return flightId;

	}

	public void setFlightId(Long flightId) {

		this.flightId = flightId;

	}

	public String getPassengerName() {

		return passengerName;

	}

	public void setPassengerName(String passengerName) {

		this.passengerName = passengerName;

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	public String getPhoneNumber() {

		return phoneNumber;

	}

	public void setPhoneNumber(String phoneNumber) {

		this.phoneNumber = phoneNumber;

	}

	public String getSeatNumber() {

		return seatNumber;

	}

	public void setSeatNumber(String seatNumber) {

		this.seatNumber = seatNumber;

	}

	public String getStatus() {

		return status;

	}

	public void setStatus(String status) {

		this.status = status;

	}

}
