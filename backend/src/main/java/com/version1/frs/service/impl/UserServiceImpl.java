package com.version1.frs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.UserRegistrationRequest;
import com.version1.frs.model.User;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String register(UserRegistrationRequest request) {
        if (userRepository.findByUserEmail(request.getUserEmail()) != null) {
            return "Email already registered.";
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setUserGender(request.getUserGender());
        user.setUserPassword(request.getUserPassword());
        user.setUserRole(request.getUserRole() != null ? request.getUserRole() : "CUSTOMER");

        userRepository.save(user);
        return "Registration successful.";
    }

    @Override
    public User authenticate(LoginRequest request) {
        User user = userRepository.findByUserEmail(request.getUserEmail());
        if (user != null && user.getUserPassword().equals(request.getUserPassword())) {
            return user;
        }
        return null;
    }
}