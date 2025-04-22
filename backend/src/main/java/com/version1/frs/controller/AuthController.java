/*
 * Copyright 2025 Version 1
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

package com.version1.frs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.LoginResponse;
import com.version1.frs.dto.UserRequest;
import com.version1.frs.model.User;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.security.JwtUtil;
import com.version1.frs.service.UserService;

import jakarta.validation.Valid;

/**
 * Controller for authentication and user registration. Handles login and
 * registration processes and issues JWT tokens for valid credentials.
 * 
 * Base URL: /api
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final UserService userService;
	
	/**
	 * Constructor for injecting required dependencies.
	 *
	 * @param authManager     Spring Security's AuthenticationManager for handling login attempts
	 * @param jwtUtil         Utility class for generating JWT tokens
	 * @param userRepository  Repository for fetching user data from database
	 * @param userService     Service to handle user registration logic
	 */

	public AuthController(
			AuthenticationManager authManager,
			JwtUtil jwtUtil,
			UserRepository userRepository,
			UserService userService) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	/**
	 * Registers a new user (CUSTOMER role by default). Accessible by
	 * unauthenticated clients.
	 * 
	 * @param request the user details (name, email, password, etc.)
	 * @return a message response indicating success or failure
	 */
	@PostMapping("/register")
	public ResponseEntity<Object> register(@Valid @RequestBody UserRequest request) {
		try {
			String message = userService.register(request);
			return new ResponseEntity<>(new MessageResponse(message), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse("Registration failed: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Authenticates a user and returns a JWT token on success. Accessible by
	 * unauthenticated clients.
	 * 
	 * @param request the login request containing email and password
	 * @return a JWT token and user role or error message if authentication fails
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest request) {
		try {
			// Authenticate using Spring Security
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword()));

			// Retrieve user details
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			User user = userRepository.findByUserEmail(request.getUserEmail());

			// Generate JWT token
			String token = jwtUtil.generateToken(user.getUserEmail(), user.getUserRole(), user.getUserId());

			// Return response with token and role
			return ResponseEntity.ok(new LoginResponse(token, user.getUserRole()));

		} catch (BadCredentialsException ex) {
			return new ResponseEntity<>(new MessageResponse("Invalid email or password"), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse("Login failed: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Inner class to standardize API response messages.
	 */
	public static class MessageResponse {
		private String message;

		public MessageResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
