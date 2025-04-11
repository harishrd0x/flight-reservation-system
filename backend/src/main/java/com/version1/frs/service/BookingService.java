package com.version1.frs.service;

import com.version1.frs.dto.BookingRequest;
import com.version1.frs.dto.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse bookFlight(BookingRequest request);
    List<BookingResponse> getBookingsByUser(int userId);
    List<BookingResponse> getAllBookings(); // For ADMIN only
}