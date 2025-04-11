package com.version1.frs.controller;

import com.version1.frs.dto.UserRegistrationRequest;
import com.version1.frs.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RegistrationController {

    @Autowired
    private UserService userService;

    // Anyone can register
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationRequest request) {
        String result = userService.register(request);
        if (result.equalsIgnoreCase("Registration successful.")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
/*
    // Optional: Only admin can create another admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/register")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody UserRegistrationRequest request) {
        request.setUserRole("ADMIN");
        return ResponseEntity.ok(userService.register(request));
    }
*/
}