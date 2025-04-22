/*
 * Copyright 2022-2025 the original author or authors.
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

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO for user registration or update requests. Validates user input using
 * Jakarta Bean Validation annotations.
 * 
 * Fields include name, email, gender, password, and role.
 * 
 * @author YourName
 * @since 1.0
 */
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

	/**
	 * Gets the user's name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user's name.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the user's email.
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the user's email.
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Gets the user's gender.
	 */
	public String getUserGender() {
		return userGender;
	}

	/**
	 * Sets the user's gender.
	 */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	/**
	 * Gets the user's password.
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * Sets the user's password.
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * Gets the user's role.
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * Sets the user's role.
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
