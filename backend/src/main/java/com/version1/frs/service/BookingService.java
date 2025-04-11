package com.version1.frs.service;

import com.version1.frs.dto.BookingRequest;
import com.version1.frs.model.Booking;

import java.util.List;

public interface BookingService {

    Booking bookFlight(BookingRequest request);

    List<Booking> getBookingsByUser(Long userId);

    Booking getBookingById(Long bookingId);

    void cancelBooking(Long bookingId);
}