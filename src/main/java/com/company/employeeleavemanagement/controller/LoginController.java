package com.company.employeeleavemanagement.controller;

import com.company.employeeleavemanagement.model.User;
import com.company.employeeleavemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String login(@RequestBody User user) {
        return userService.findByUsername(user.getUsername())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .map(u -> {
                    if ("admin".equals(u.getRole())) {
                        return "admin-dashboard";
                    } else if ("user".equals(u.getRole())) {
                        return "user-dashboard";
                    } else {
                        return "unknown-role";
                    }
                })
                .orElse("invalid-credentials");
    }
}
