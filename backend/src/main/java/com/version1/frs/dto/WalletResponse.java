/*
 * Copyright 2002-2024 the original author or authors.
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

package com.version1.frs.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Wallet Response. This class is used to represent the
 * details of a wallet.
 */
public class WalletResponse {

	/**
	 * The unique identifier for the wallet.
	 */
	private Long walletId;

	/**
	 * The ID of the user who owns the wallet.
	 */
	private Long userId;

	/**
	 * The balance in the wallet.
	 */
	private BigDecimal balance;

	// Getters and Setters

	/**
	 * Gets the wallet ID.
	 * 
	 * @return the wallet ID
	 */
	public Long getWalletId() {
		return walletId;
	}

	/**
	 * Sets the wallet ID.
	 * 
	 * @param walletId the wallet ID to set
	 */
	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	/**
	 * Gets the user ID.
	 * 
	 * @return the user ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID.
	 * 
	 * @param userId the user ID to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the balance in the wallet.
	 * 
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Sets the balance in the wallet.
	 * 
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
