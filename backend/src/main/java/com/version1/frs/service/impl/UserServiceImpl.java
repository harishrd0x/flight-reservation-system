package com.version1.frs.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.UserRequest;
import com.version1.frs.dto.UserResponse;
import com.version1.frs.model.User;
import com.version1.frs.model.Wallet;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.security.UserDetailsImpl;
import com.version1.frs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(UserRequest request) {
        if (userRepository.findByUserEmail(request.getUserEmail()) != null) {
            return "Email already registered.";
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setUserGender(request.getUserGender());
        // Encode password before saving
        user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        user.setUserRole(request.getUserRole() != null ? request.getUserRole() : "CUSTOMER");

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(BigDecimal.ZERO);
        user.setWallet(wallet); // creating bidirectional link

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
    public Optional<UserResponse> getUserById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(this::mapToResponse); // Converts User to UserResponse inside Optional
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        // Get the currently authenticated user from the token
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((UserDetailsImpl) userDetails).getId();  // Get userId from the token

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the user details if provided in the request
        if (userRequest.getUserName() != null) {
            user.setUserName(userRequest.getUserName());
        }
        if (userRequest.getUserGender() != null) {
            user.setUserGender(userRequest.getUserGender());
        }
        if (userRequest.getUserPassword() != null) {
            user.setUserPassword(passwordEncoder.encode(userRequest.getUserPassword()));
        }
        if (userRequest.getUserRole() != null) {
            user.setUserRole(userRequest.getUserRole());
        }

        userRepository.save(user);
        return mapToResponse(user);
    }

    @Override
    public void deleteAccount() {
        // Get the currently authenticated user from the token
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((UserDetailsImpl) userDetails).getId();  // Get userId from the token

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    // Mapping method to convert User to UserResponse
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
