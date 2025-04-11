package com.version1.frs.controller;

import com.version1.frs.dto.WalletRequest;
import com.version1.frs.model.Wallet;
import com.version1.frs.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/add")
    public ResponseEntity<String> addMoney(@RequestBody WalletRequest request,
                                           @RequestParam String userRole) {
        if (!userRole.equalsIgnoreCase("CUSTOMER")) {
            return ResponseEntity.status(403).body("Only CUSTOMERS can add money to wallet.");
        }

        return ResponseEntity.ok(walletService.addMoney(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable int userId,
                                            @RequestParam String userRole) {
        if (!userRole.equalsIgnoreCase("CUSTOMER")) {
            return ResponseEntity.status(403).build();
        }

        Wallet wallet = walletService.getWalletByUserId(userId);
        return wallet != null
                ? ResponseEntity.ok(wallet)
                : ResponseEntity.notFound().build();
    }
}