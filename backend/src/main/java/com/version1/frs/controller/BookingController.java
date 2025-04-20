package com.version1.frs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.BookingRequest;
import com.version1.frs.dto.BookingResponse;
import com.version1.frs.security.UserDetailsImpl;
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
    public ResponseEntity<BookingResponse> bookFlight(
            @Valid @RequestBody BookingRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getId(); // Extracted directly
        return ResponseEntity.ok(bookingService.bookFlight(request, userId));
    }
	
	// View all bookings of a user (CUSTOMER only) - Use userId from token
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/user")
	public ResponseEntity<List<BookingResponse>> getUserBookings(@AuthenticationPrincipal UserDetailsImpl userDetails) {
	    Long userId = userDetails.getId();  // Access userId from UserDetailsImpl
	    return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
	}

	// View all bookings (ADMIN only) with optional filter by customerId
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<BookingResponse>> getAllBookings(@RequestParam(required = false) Long customerId) {
		return ResponseEntity.ok(bookingService.getAllBookings(customerId));
	}

	// Get booking by ID (CUSTOMER can only view their own booking, ADMIN can view any booking)
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<BookingResponse> getBookingById(
	        @PathVariable Long id,
	        @AuthenticationPrincipal UserDetailsImpl userDetails
	) {
	    BookingResponse booking = bookingService.getBookingById(id);

	    // if CUSTOMER, ensure they own it
	    boolean isCustomer = userDetails.getAuthorities().stream()
	        .anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"));
	    if (isCustomer && !booking.getCustomerId().equals(userDetails.getId())) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }

	    return ResponseEntity.ok(booking);
	}


	// Delete booking (CUSTOMER only for their own booking, ADMIN for any booking)
	@PreAuthorize("hasRole('CUSTOMER') and @bookingSecurity.checkOwner(#id) or hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);
		return ResponseEntity.noContent().build();
	}
}