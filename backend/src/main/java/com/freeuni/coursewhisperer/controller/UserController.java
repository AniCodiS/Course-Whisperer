package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.ChangePasswordDTO;
import com.freeuni.coursewhisperer.data.api.dto.CreatedUserDTO;
import com.freeuni.coursewhisperer.data.api.dto.UpdateUserDTO;
import com.freeuni.coursewhisperer.data.api.dto.UserDTO;
import com.freeuni.coursewhisperer.service.LoginService;
import com.freeuni.coursewhisperer.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final LoginService loginService;

    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/create")
    public CreatedUserDTO createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @PutMapping("/{username}")
    public UpdateUserDTO updateUser(@PathVariable String username, @RequestBody UpdateUserDTO updateUserDTO) {
        return userService.updateUser(username, updateUserDTO);
    }

    @PutMapping("/{username}/change-password")
    public boolean changePassword(@PathVariable String username, @RequestBody ChangePasswordDTO changePasswordDTO) {
        return userService.changePassword(username, changePasswordDTO);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @GetMapping("/login")
    public boolean login(@RequestParam String username, @RequestParam String password) {
        return loginService.login(username, password);
    }
}
