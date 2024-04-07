package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.*;
import com.freeuni.coursewhisperer.exception.CourseWhispererException;
import com.freeuni.coursewhisperer.service.LoginService;
import com.freeuni.coursewhisperer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final LoginService loginService;

    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        try {
            return ResponseEntity.ok().body(userService.getAllUsers());
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(List.of(new UserResponse(e.getErrorDescription())));
        }
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok().body(userService.getUserByUsername(username));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new UserResponse(e.getErrorDescription()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok().body(userService.createUser(user));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new UserResponse(e.getErrorDescription()));
        }
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<UpdatedUserDTO> updateUser(@PathVariable String username, @RequestBody UpdateUserDTO updateUserDTO) {
        try {
            return ResponseEntity.ok().body(userService.updateUser(username, updateUserDTO));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new UpdatedUserDTO(e.getErrorDescription()));
        }
    }

    @PutMapping("/{username}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable String username, @RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            return ResponseEntity.ok().body(userService.changePassword(username, changePasswordDTO));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getErrorDescription());
        }
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getErrorDescription());
        }
        return ResponseEntity.ok().body("User deleted successfully");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            return ResponseEntity.ok().body(loginService.login(username, password));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getErrorDescription());
        }
    }
}
