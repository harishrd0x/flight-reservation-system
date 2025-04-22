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

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object for Wallet Request. This class is used to capture the
 * details of a wallet request.
 */
public class WalletRequest {

	/**
	 * The balance in the wallet. Must not be null and must be positive.
	 */
	@NotNull(message = "Balance cannot be null")
	@Positive(message = "Balance must be positive")
	private BigDecimal balance;

	// Getters and Setters

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
