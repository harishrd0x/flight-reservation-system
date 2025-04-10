package com.version1.frs.service;

public interface UserService {
    boolean authenticate(String email, String password);
}