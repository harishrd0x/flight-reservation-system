/*
 * Copyright 2022-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.version1.frs.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing the response returned after a
 * successful booking operation.
 *
 * <p>
 * This DTO encapsulates details such as booking ID, customer ID, associated
 * flight ID, booking timestamp, and the total amount charged.
 * </p>
 * 
 * @author YourName
 * @since 1.0
 */
public class BookingResponse {

	/**
	 * Unique identifier for the booking.
	 */
	private Long bookingId;

	/**
	 * Identifier of the customer who made the booking.
	 */
	private Long customerId;

	/**
	 * Identifier of the flight associated with the booking.
	 */
	private Long flightId;

	/**
	 * Timestamp when the booking was created.
	 */
	private LocalDateTime bookingTime;

	/**
	 * Total amount charged for the booking.
	 */
	private BigDecimal totalAmount;
  
  private List<PassengerResponse> passengers;

	/**
	 * Default constructor.
	 */
	public BookingResponse() {
	}

	/**
	 * Parameterized constructor to initialize all fields.
	 *
	 * @param bookingId   the booking ID
	 * @param customerId  the customer ID
	 * @param flightId    the flight ID
	 * @param bookingTime the date and time of booking
	 * @param totalAmount the total amount paid
	 */
    public BookingResponse(Long bookingId, Long customerId, Long flightId,
                           LocalDateTime bookingTime, BigDecimal totalAmount,
                           List<PassengerResponse> passengers) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.flightId = flightId;
        this.bookingTime = bookingTime;
        this.totalAmount = totalAmount;
        this.passengers = passengers;
    }

	// Getters and Setters

	/**
	 * Gets the booking ID.
	 * 
	 * @return the booking ID
	 */
	public Long getBookingId() {
		return bookingId;
	}

	/**
	 * Sets the booking ID.
	 * 
	 * @param bookingId the booking ID to set
	 */
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * Gets the customer ID.
	 * 
	 * @return the customer ID
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the customer ID.
	 * 
	 * @param customerId the customer ID to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * Gets the flight ID.
	 * 
	 * @return the flight ID
	 */
	public Long getFlightId() {
		return flightId;
	}

	/**
	 * Sets the flight ID.
	 * 
	 * @param flightId the flight ID to set
	 */
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	/**
	 * Gets the booking time.
	 * 
	 * @return the booking time
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
	 * Gets the total amount paid for the booking.
	 * 
	 * @return the total amount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Sets the total amount paid for the booking.
	 * 
	 * @param totalAmount the total amount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
  
  public List<PassengerResponse> getPassengers() {
        return passengers;
  }
  
  public void setPassengers() {
    this.passengers = passengers;
  }
}
