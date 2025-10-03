package com.example.spring.security.test.spring_security_test.controller;

import com.example.spring.security.test.spring_security_test.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/")
public class MainController {
    private final AdminService adminService;

    public MainController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path="/admin")
    public String admin() {
        return "Hello Admin";
    }
    @GetMapping(path="/user")
    public String user() {
        return "Hello User";
    }
    @GetMapping(path="/public")
    public String publicUser() {
        return "Hello Public";
    }
    @GetMapping(path="/secret")
    public String secretUser() {
        return adminService.admin();
    }
}
