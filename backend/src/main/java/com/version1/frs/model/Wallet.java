package com.version1.frs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_WALLETS")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WALLET_ID")
    private Long walletId;

    @Column(name = "USER_ID", nullable = false, unique = true)
    private int userId;

    @Column(name = "BALANCE", nullable = false)
    private double balance;

    // Getters and Setters
    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}