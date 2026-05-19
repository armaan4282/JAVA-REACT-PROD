package com.example.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.app.entity.User;
import com.example.app.repository.UserRepository;

@RestController
@RequestMapping("/api")

// ✅ FIXED CORS (production ready)
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository repository;

    // ✅ REGISTER API
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repository.save(user);
    }

    // ✅ LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        Optional<User> dbUser = repository.findByEmail(user.getEmail());

        if (dbUser.isPresent() &&
            dbUser.get().getPassword().equals(user.getPassword())) {
            return "Login Success";
        }

        return "Invalid Credentials";
    }
}
