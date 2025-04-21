package com.version1.frs.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing a user's wallet. Maps to the TBL_WALLETS table in
 * the database. Each wallet is uniquely associated with one user.
 */
@Entity
@Table(name = "TBL_WALLETS")
public class Wallet {

	// -------------------- Fields --------------------

	/**
	 * Unique identifier for the wallet. Auto-generated primary key mapped to the
	 * 'WALLET_ID' column.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WALLET_ID")
	private Long walletId;

	/**
	 * One-to-one relationship with the User entity. Each user has exactly one
	 * wallet.
	 */
	@OneToOne
	@JoinColumn(name = "USER_ID", nullable = false, unique = true)
	private User user;

	/**
	 * Current balance in the wallet. Stored as a decimal value in the 'BALANCE'
	 * column.
	 */
	@Column(name = "BALANCE", nullable = false)
	private BigDecimal balance;

	// -------------------- Getters and Setters --------------------

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
	 * Gets the user associated with the wallet.
	 *
	 * @return the associated user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user associated with the wallet.
	 *
	 * @param user the user to associate
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the wallet balance.
	 *
	 * @return the wallet balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Sets the wallet balance.
	 *
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
