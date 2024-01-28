package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.config.GetLoggedInUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String getCurrentUser() {
        return GetLoggedInUser.getCurrentUsername();
    }
}