package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String username, String password) {
        if (userRepository.findByUsernameAndPassword(username, password) == null) {
            throw ExceptionFactory.UserNotFound();
        }
        return "Login successful";
    }
}
