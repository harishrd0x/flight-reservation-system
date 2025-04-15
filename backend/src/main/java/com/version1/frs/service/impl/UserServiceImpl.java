package com.version1.frs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.UserRegistrationRequest;
import com.version1.frs.dto.UserResponse;
import com.version1.frs.model.User;
import com.version1.frs.model.Wallet;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(UserRegistrationRequest request) {
        if (userRepository.findByUserEmail(request.getUserEmail()) != null) {
            return "Email already registered.";
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setUserGender(request.getUserGender());
        
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        user.setWallet(wallet); // creating bidirectional link
        
        userRepository.save(user);

        // Encode password before saving
        user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));

        user.setUserRole(request.getUserRole() != null ? request.getUserRole() : "CUSTOMER");

        userRepository.save(user);
        return "Registration successful.";
    }

    @Override
    public User authenticate(LoginRequest request) {
        User user = userRepository.findByUserEmail(request.getUserEmail());
        if (user != null && passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
            return user;
        }
        return null;
    }
    
    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setUserEmail(user.getUserEmail());
        response.setUserGender(user.getUserGender());
        response.setUserRole(user.getUserRole());
        return response;
    }

    // Mapping method
    private UserResponse mapToResponse(User user) {
        UserResponse res = new UserResponse();
        res.setUserId(user.getUserId());
        res.setUserName(user.getUserName());
        res.setUserEmail(user.getUserEmail());
        res.setUserGender(user.getUserGender());
        res.setUserRole(user.getUserRole());
        return res;
    }
}