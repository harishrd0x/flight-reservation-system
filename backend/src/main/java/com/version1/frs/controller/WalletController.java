package com.version1.frs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.WalletRequest;
import com.version1.frs.dto.WalletResponse;
import com.version1.frs.security.UserDetailsImpl;
import com.version1.frs.service.WalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/wallet")
@CrossOrigin
public class WalletController {

	@Autowired
	private WalletService walletService;

	// View wallet
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping
	public ResponseEntity<WalletResponse> getWallet(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		// No need to manually extract user ID from the token
		Long userId = userDetails.getId(); // Assuming getId() method exists in your UserDetailsImpl
		WalletResponse response = walletService.getWalletByUserId(userId);
		return ResponseEntity.ok(response);
	}

	// Add money
	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/add")
	public ResponseEntity<WalletResponse> addMoney(@Valid @RequestBody WalletRequest request,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		WalletResponse response = walletService.addMoney(userDetails.getId(), request);

		return ResponseEntity.ok(response);
	}
}
