package com.version1.frs.dto;

import java.math.BigDecimal;

public class WalletResponse {

    private Long walletId;
    private Long userId;
    private BigDecimal  balance;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal  getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal  balance) {
        this.balance = balance;
    }
}