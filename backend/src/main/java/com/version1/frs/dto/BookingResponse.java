package com.version1.frs.dto;

import java.time.LocalDateTime;

public class BookingResponse {
    private Long bookingId;
    private Long userId;
    private Long flightId;
    private String flightNumber;
    private LocalDateTime bookingTime;

    public BookingResponse(Long bookingId, Long userId, Long flightId, String flightNumber, LocalDateTime bookingTime) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.bookingTime = bookingTime;
    }

    // Getters & Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}