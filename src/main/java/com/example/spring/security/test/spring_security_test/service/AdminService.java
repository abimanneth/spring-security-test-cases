package com.example.spring.security.test.spring_security_test.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Hello Admin with secret code:"+Math.random();
    }
}
