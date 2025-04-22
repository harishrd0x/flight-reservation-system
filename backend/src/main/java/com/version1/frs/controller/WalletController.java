/*
 * Copyright 2025 Version 1
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.version1.frs.controller;

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

/**
 * Controller for handling wallet operations. Allows customers to view their
 * wallet balance and add money to it.
 *
 * Base URL: /api/wallet
 */
@RestController
@RequestMapping("/api/wallet")
@CrossOrigin
public class WalletController {

	private final WalletService walletService;

	/**
	 * Constructor-based injection for {@link WalletService}.
	 *
	 * @param walletService the wallet service to handle wallet-related operations
	 */
	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}

	/**
	 * Retrieves the wallet details for the currently authenticated customer.
	 *
	 * @param userDetails the authenticated user's details (automatically injected)
	 * @return the wallet information including current balance
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping
	public ResponseEntity<WalletResponse> getWallet(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		Long userId = userDetails.getId();
		WalletResponse response = walletService.getWalletByUserId(userId);
		return ResponseEntity.ok(response);
	}

	/**
	 * Adds money to the wallet of the currently authenticated customer.
	 *
	 * @param request     the wallet request containing the amount to add
	 * @param userDetails the authenticated user's details
	 * @return the updated wallet information
	 */
	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/add")
	public ResponseEntity<WalletResponse> addMoney(@Valid @RequestBody WalletRequest request,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		WalletResponse response = walletService.addMoney(userDetails.getId(), request);
		return ResponseEntity.ok(response);
	}
}
