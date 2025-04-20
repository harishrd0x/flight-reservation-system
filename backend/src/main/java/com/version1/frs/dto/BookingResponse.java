package com.version1.frs.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingResponse {
    private Long bookingId;
    private Long customerId;
    private Long flightId;
    private LocalDateTime bookingTime;
    private BigDecimal totalAmount;
    

    public BookingResponse() {}

    public BookingResponse(Long bookingId, Long customerId, Long flightId, LocalDateTime bookingTime, BigDecimal totalAmount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.flightId = flightId;
        this.bookingTime = bookingTime;
        this.totalAmount = totalAmount;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
    
}
