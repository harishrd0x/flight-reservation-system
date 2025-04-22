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

/**
 * Data Transfer Object for User Response. This class is used to represent the
 * details of a user.
 */
public class UserResponse {

	/**
	 * The unique identifier for the user.
	 */
	private long userId;

	/**
	 * The name of the user.
	 */
	private String userName;

	/**
	 * The email of the user.
	 */
	private String userEmail;

	/**
	 * The gender of the user.
	 */
	private String userGender;

	/**
	 * The role of the user.
	 */
	private String userRole;

	// Getters & Setters

	/**
	 * Gets the user ID.
	 * 
	 * @return the user ID
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID.
	 * 
	 * @param userId the user ID to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName the user name to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the user email.
	 * 
	 * @return the user email
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the user email.
	 * 
	 * @param userEmail the user email to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Gets the user gender.
	 * 
	 * @return the user gender
	 */
	public String getUserGender() {
		return userGender;
	}

	/**
	 * Sets the user gender.
	 * 
	 * @param userGender the user gender to set
	 */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	/**
	 * Gets the user role.
	 * 
	 * @return the user role
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * Sets the user role.
	 * 
	 * @param userRole the user role to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
