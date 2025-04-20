package com.version1.frs.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WalletRequest {
	
	@NotNull(message = "Balance cannot be null")
	@Positive(message = "Balance must be positive")
	private BigDecimal balance;

    // Getters and Setters

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
