package com.version1.frs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/**
 * Entity class representing a system user. Maps to the TBL_USERS table in the
 * database. Each user can have one associated wallet and can hold roles like
 * USER or ADMIN.
 */
@Entity
@Table(name = "TBL_USERS")
public class User {

	// -------------------- Fields --------------------

	/**
	 * Unique identifier for the user. Auto-generated primary key mapped to the
	 * 'USER_ID' column.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;

	/**
	 * The full name of the user. Mapped to the 'USER_NAME' column.
	 */
	@Size(max = 100)
	@Column(name = "USER_NAME", nullable = false, length = 100)
	private String userName;

	/**
	 * Unique email of the user. Used as a login credential. Mapped to 'USER_EMAIL'.
	 */
	@Column(name = "USER_EMAIL", nullable = false, unique = true, length = 150)
	private String userEmail;

	/**
	 * Gender of the user. Mapped to the 'USER_GENDER' column.
	 */
	@Column(name = "USER_GENDER", nullable = false, length = 10)
	private String userGender;

	/**
	 * Encrypted password of the user. Mapped to 'USER_PASSWORD'. Should be stored
	 * in hashed format.
	 */
	@Column(name = "USER_PASSWORD", nullable = false, length = 255)
	private String userPassword;

	/**
	 * Role assigned to the user (e.g., USER, ADMIN). Mapped to 'USER_ROLE'.
	 */
	@Column(name = "USER_ROLE", nullable = false, length = 20)
	private String userRole;

	/**
	 * One-to-one relationship with the Wallet entity. Cascade all operations.
	 * Wallet is removed if user is deleted.
	 */
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Wallet wallet;

	// -------------------- Getters and Setters --------------------

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
	 * Gets the user's full name.
	 *
	 * @return the user's name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user's full name.
	 *
	 * @param userName the name to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the user's email.
	 *
	 * @return the user's email
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the user's email.
	 *
	 * @param userEmail the email to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Gets the user's gender.
	 *
	 * @return the gender
	 */
	public String getUserGender() {
		return userGender;
	}

	/**
	 * Sets the user's gender.
	 *
	 * @param userGender the gender to set
	 */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	/**
	 * Gets the user's password (encrypted).
	 *
	 * @return the password
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * Sets the user's encrypted password.
	 *
	 * @param userPassword the password to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * Gets the user's role (e.g., USER or ADMIN).
	 *
	 * @return the user role
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * Sets the user's role.
	 *
	 * @param userRole the role to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * Gets the wallet associated with the user.
	 *
	 * @return the wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}

	/**
	 * Sets the wallet associated with the user.
	 *
	 * @param wallet the wallet to set
	 */
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
}
