package com.version1.frs.controller;

import com.version1.frs.dto.WalletRequest;
import com.version1.frs.dto.WalletResponse;
import com.version1.frs.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
@CrossOrigin
public class WalletController {

    @Autowired
    private WalletService walletService;

    // Create wallet (once)
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create")
    public ResponseEntity<WalletResponse> createWallet(@RequestParam Long userId) {
        WalletResponse response = walletService.createWallet(userId);
        return ResponseEntity.ok(response);
    }

    // View wallet
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{userId}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable Long userId) {
        WalletResponse response = walletService.getWalletByUserId(userId);
        return ResponseEntity.ok(response);
    }

    // Add money
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/add")
    public ResponseEntity<WalletResponse> addMoney(@RequestBody WalletRequest request) {
        WalletResponse response = walletService.addMoney(request);
        return ResponseEntity.ok(response);
    }

    // Make payment (used internally during booking)
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/pay")
    public ResponseEntity<WalletResponse> makePayment(@RequestBody WalletRequest request) {
        WalletResponse response = walletService.makePayment(request);
        return ResponseEntity.ok(response);
    }
}