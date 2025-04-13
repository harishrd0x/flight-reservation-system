package com.version1.frs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.BookingResponse;
import com.version1.frs.dto.ReviewResponse;
import com.version1.frs.dto.UserResponse;
import com.version1.frs.dto.WalletResponse;
import com.version1.frs.service.BookingService;
import com.version1.frs.service.ReviewService;
import com.version1.frs.service.UserService;
import com.version1.frs.service.WalletService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired private BookingService bookingService;
    @Autowired private UserService userService;
    @Autowired private ReviewService reviewService;
    @Autowired private WalletService walletService;

    @GetMapping("/users")
    public List<UserResponse> viewAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/reviews")
    public List<ReviewResponse> viewAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/wallet/{userId}")
    public ResponseEntity<WalletResponse> viewUserWallet(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getWalletByUserId(userId));
    }
    
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}