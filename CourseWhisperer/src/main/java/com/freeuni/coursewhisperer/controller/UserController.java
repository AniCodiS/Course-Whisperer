package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.model.api.UserLoginRequest;
import com.freeuni.coursewhisperer.model.api.UserSignupRequest;
import com.freeuni.coursewhisperer.model.db.User;
import com.freeuni.coursewhisperer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
        // Retrieve the user by username
        User user = userService.findByUsername(userLoginRequest.getUsername());

        // Check if the user exists and the password matches (in a real application, compare hashed passwords)
        if (user != null && user.getPassword().equals(userLoginRequest.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignupRequest userSignupRequest) {
        // Check if username or email is already taken
        if (userService.findByUsername(userSignupRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        if (userService.findByMail(userSignupRequest.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }

        // Create a new user
        User user = new User();
        user.setUsername(userSignupRequest.getUsername());
        user.setPassword(userSignupRequest.getPassword());
        user.setMail(userSignupRequest.getEmail());

        // Save the user
        userService.createUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
