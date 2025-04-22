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

/**
 * DTO for login response containing authentication token and user role.
 * 
 * Used as the response body after successful authentication.
 * 
 * @author YourName
 * @since 1.0
 */
public class LoginResponse {

	private String token;
	private String role;

	/**
	 * Default constructor.
	 */
	public LoginResponse() {
	}

	/**
	 * Parameterized constructor for LoginResponse.
	 * 
	 * @param token the authentication token
	 * @param role  the user's role (e.g., USER, ADMIN)
	 */
	public LoginResponse(String token, String role) {
		this.token = token;
		this.role = role;
	}

	/**
	 * Gets the authentication token.
	 * 
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the authentication token.
	 * 
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the user role.
	 * 
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the user role.
	 * 
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
