package com.version1.frs.controller;

import com.version1.frs.dto.BookingResponse;
import com.version1.frs.dto.ReviewResponse;
import com.version1.frs.dto.UserResponse;
import com.version1.frs.dto.WalletResponse;
import com.version1.frs.service.BookingService;
import com.version1.frs.service.ReviewService;
import com.version1.frs.service.UserService;
import com.version1.frs.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}