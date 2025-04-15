package com.version1.frs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {

	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name should not exceed 100 characters")
	private String userName;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	@Size(max = 150, message = "Email should not exceed 150 characters")
	private String userEmail;

	@NotBlank(message = "Gender is required")
	@Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be either 'Male', 'Female' or 'Other'")
	private String userGender;

	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password should have at least 8 characters")
	private String userPassword;
	
	@NotBlank(message = "Role is required")
	@Size(max = 20, message = "Role should not exceed 20 characters")
	private String userRole;


	// Getters and Setters
	
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
}
