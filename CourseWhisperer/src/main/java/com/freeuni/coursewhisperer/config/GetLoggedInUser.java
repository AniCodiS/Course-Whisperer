package com.freeuni.coursewhisperer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetLoggedInUser {

    @Bean
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return "Current User: " + username;
        } else {
            return "User not authenticated";
        }
    }
}
