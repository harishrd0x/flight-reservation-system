package com.version1.frs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.BookingRequest;
import com.version1.frs.dto.BookingResponse;
import com.version1.frs.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Book flight (CUSTOMER only)
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<BookingResponse> bookFlight(@Valid @RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.bookFlight(request));
    }

    // View all bookings of a user (CUSTOMER only)
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getUserBookings(@PathVariable int userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }

    // View all bookings (ADMIN only)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}