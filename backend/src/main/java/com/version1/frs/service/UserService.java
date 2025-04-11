package com.version1.frs.service;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.UserRegistrationRequest;
import com.version1.frs.model.User;

public interface UserService {
    String register(UserRegistrationRequest request);
    User authenticate(LoginRequest request);
}