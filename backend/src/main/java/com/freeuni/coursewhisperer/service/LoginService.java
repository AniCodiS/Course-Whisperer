package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password) != null;
        // TODO: throw exception
    }
}
