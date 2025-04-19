package com.version1.frs.service;

import java.util.List;
import java.util.Optional;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.UserRequest;
import com.version1.frs.dto.UserResponse;
import com.version1.frs.model.User;

public interface UserService {

    List<UserResponse> getAllUsers();

    String register(UserRequest request);

    Optional<UserResponse> getUserById(Long id);

    UserResponse updateUser(UserRequest userRequest);

    void deleteAccount();

	User authenticate(LoginRequest request);

}
