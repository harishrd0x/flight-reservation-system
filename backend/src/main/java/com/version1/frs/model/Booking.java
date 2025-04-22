package com.version1.frs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entity class representing a booking in the system. This class maps to the
 * 'TBL_BOOKINGS' table in the database.
 */
@Entity
@Table(name = "TBL_BOOKINGS")
public class Booking {

	// -------------------- Fields --------------------

	/**
	 * Unique identifier for the booking. Mapped to the 'BOOKING_ID' column in the
	 * database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOKING_ID")
	private Long bookingId;

	/**
	 * User who made the booking. Represents a many-to-one relationship with the
	 * {@link User} entity. Mapped to the 'USER_ID' column in the database.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	/**
	 * Flight that is booked. Represents a many-to-one relationship with the
	 * {@link Flight} entity. Mapped to the 'FLIGHT_ID' column in the database.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLIGHT_ID", nullable = false)
	private Flight flight;

	/**
	 * The time when the booking was made. Mapped to the 'BOOKING_TIME' column in
	 * the database.
	 */
	@Column(name = "BOOKING_TIME", nullable = false)
	private LocalDateTime bookingTime;

	/**
	 * The total amount for the booking. Mapped to the 'TOTAL_AMOUNT' column in the
	 * database.
	 */
	@Column(name = "TOTAL_AMOUNT", nullable = false)
	private BigDecimal totalAmount;
	
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BookedTicket> bookedTickets = new ArrayList<>();

	// -------------------- Getters and Setters --------------------

	/**
	 * Gets the unique identifier for the booking.
	 * 
	 * @return the booking ID
	 */
	public Long getBookingId() {
		return bookingId;
	}

	/**
	 * Sets the unique identifier for the booking.
	 * 
	 * @param bookingId the booking ID to set
	 */
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * Gets the user who made the booking.
	 * 
	 * @return the user who made the booking
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user who made the booking.
	 * 
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the flight that is booked.
	 * 
	 * @return the booked flight
	 */
	public Flight getFlight() {
		return flight;
	}

	/**
	 * Sets the flight that is booked.
	 * 
	 * @param flight the flight to set
	 */
	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	/**
	 * Gets the booking time.
	 * 
	 * @return the time when the booking was made
	 */
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	/**
	 * Sets the booking time.
	 * 
	 * @param bookingTime the booking time to set
	 */
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	/**
	 * Gets the total amount for the booking.
	 * 
	 * @return the total amount for the booking
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Sets the total amount for the booking.
	 * 
	 * @param totalAmount the total amount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<BookedTicket> getBookedTickets() {
		return bookedTickets;
	}

	public void setBookedTickets(List<BookedTicket> bookedTickets) {
		this.bookedTickets = bookedTickets;
	}
	
}
