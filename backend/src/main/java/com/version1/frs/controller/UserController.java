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

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.UserRequest;
import com.version1.frs.dto.UserResponse;
import com.version1.frs.security.UserDetailsImpl;
import com.version1.frs.service.UserService;

/**
 * Controller for managing users. Allows admins to manage all users and
 * customers/admins to manage their own profile.
 *
 * Base URL: /api/users
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

	private final UserService userService;

	/**
	 * Constructor-based injection for {@link UserService}.
	 *
	 * @param userService the service to manage user-related operations
	 */
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Retrieves a list of all users. Accessible only by admins.
	 *
	 * @return list of all users in the system
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * Retrieves user details by user ID. Accessible only by admins.
	 *
	 * @param id the ID of the user
	 * @return user details if found, otherwise 404
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
		Optional<UserResponse> userOpt = userService.getUserById(id);
		return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Retrieves the profile of the currently authenticated user. Accessible by
	 * admins and customers.
	 *
	 * @return profile details of the logged-in user
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	@GetMapping("/profile")
	public ResponseEntity<UserResponse> getProfile() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = ((UserDetailsImpl) userDetails).getId();

		Optional<UserResponse> userOpt = userService.getUserById(id);
		return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Updates the information of the currently authenticated user. Accessible by
	 * admins and customers.
	 *
	 * @param userRequest the updated user details
	 * @return updated user response
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	@PutMapping("/update")
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
		UserResponse updatedUser = userService.updateUser(userRequest);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 * Deletes the currently authenticated user's account. Accessible by admins and
	 * customers.
	 *
	 * @return confirmation message
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAccount() {
		userService.deleteAccount();
		return ResponseEntity.ok("Account deleted successfully");
	}
}
