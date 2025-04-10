package com.version1.frs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.model.User;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean authenticate(String email, String password) {
        User user = userRepository.findByUserEmail(email);
        return user != null && user.getUserPassword().equals(password);
    }
}