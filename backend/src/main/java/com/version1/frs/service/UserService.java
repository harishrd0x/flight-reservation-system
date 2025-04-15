package com.version1.frs.service;

import java.util.List;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.UserRequest;
import com.version1.frs.dto.UserResponse;
import com.version1.frs.model.User;

public interface UserService {

	List<UserResponse> getAllUsers();

	// Register a new user (admin or customer)
	String register(UserRequest request);

	// Authenticate and return user (used internally for login)
	User authenticate(LoginRequest request);
	
	UserResponse getUserById(Long id);

}
