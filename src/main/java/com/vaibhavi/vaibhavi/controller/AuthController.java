package com.vaibhavi.vaibhavi.controller;

import com.vaibhavi.vaibhavi.auth.LoginRequest;
import com.vaibhavi.vaibhavi.entity.User;
import com.vaibhavi.vaibhavi.repository.UserRepository;
import com.vaibhavi.vaibhavi.security.JwtUtil;
import com.vaibhavi.vaibhavi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    // ✅ REGISTER (with BCrypt hashing)
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try {
            userService.register(user); // 🔥 hashes password
            return "User registered successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    // ✅ LOGIN (with BCrypt match)
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        try {
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // 🔥 IMPORTANT: use BCrypt match
            if (!userService.getPasswordEncoder()
                    .matches(request.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid password");
            }

            // 🔐 Generate JWT
            return jwtUtil.generateToken(user.getUsername());

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    // ✅ PROTECTED TEST API
    @GetMapping("/test")
    public String test() {
        return "Protected API is working!";
    }
}