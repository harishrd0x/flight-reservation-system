package com.version1.frs.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WalletRequest {
	
    @NotNull(message = "Balance cannot be null")
    @Positive(message = "Balance must be positive")
    private Double balance;

    // Getters and Setters

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
