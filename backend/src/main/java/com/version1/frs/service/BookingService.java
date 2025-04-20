package com.version1.frs.service;

import java.util.List;

import com.version1.frs.dto.BookingRequest;
import com.version1.frs.dto.BookingResponse;

public interface BookingService {
    BookingResponse bookFlight(BookingRequest request, Long userId);
    List<BookingResponse> getBookingsByUser(Long userId);
    List<BookingResponse> getAllBookings(Long customerId); // Optional filter
    BookingResponse getBookingById(Long id);
    void deleteBooking(Long id);
}
