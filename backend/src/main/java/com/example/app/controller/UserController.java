package com.example.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.entity.User;
import com.example.app.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return repository.findByEmail(user.getEmail())
                .map(dbUser -> {
                    if (dbUser.getPassword() != null &&
                        dbUser.getPassword().equals(user.getPassword())) {
                        return "Login Success";
                    }
                    return "Invalid Credentials";
                })
                .orElse("Invalid Credentials");
    }
}
