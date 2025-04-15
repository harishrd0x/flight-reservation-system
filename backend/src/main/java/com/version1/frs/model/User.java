package com.version1.frs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TBL_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long userId;

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    @Column(name = "USER_NAME", nullable = false, length = 100)
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "USER_EMAIL", nullable = false, unique = true, length = 150)
    private String userEmail;

    @NotBlank(message = "Gender is required")
    @Size(max = 10)
    @Column(name = "USER_GENDER", nullable = false, length = 10)
    private String userGender;

    @NotBlank(message = "Password is required")
    @Size(min = 8)
    @Column(name = "USER_PASSWORD", nullable = false, length = 255)
    private String userPassword;

    @NotBlank(message = "Role is required")
    @Column(name = "USER_ROLE", nullable = false, length = 20)
    private String userRole;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Wallet wallet;

    // --- Getters and Setters ---

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
