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

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // Admin can view all users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // Admin can view a specific user by ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        Optional<UserResponse> userOpt = userService.getUserById(id);
        return userOpt.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Users can view their own profile
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile() {
        // Get the currently authenticated user from the token
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = ((UserDetailsImpl) userDetails).getId();  // Get userId from the token

        Optional<UserResponse> userOpt = userService.getUserById(id);
        return userOpt.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Users can update their own information
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        UserResponse updatedUser = userService.updateUser(userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    // Users can delete their account
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount() {
        userService.deleteAccount();
        return ResponseEntity.ok("Account deleted successfully");
    }
}
