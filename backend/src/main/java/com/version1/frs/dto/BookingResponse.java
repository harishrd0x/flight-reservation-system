package com.version1.frs.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BookingResponse {

    private Long bookingId;
    private Long customerId;
    private Long flightId;
    private LocalDateTime bookingTime;
    private BigDecimal totalAmount;
    private List<PassengerResponse> passengers;

    // Constructor
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

    // Getters
    public Long getBookingId() {
        return bookingId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List<PassengerResponse> getPassengers() {
        return passengers;
    }
}
