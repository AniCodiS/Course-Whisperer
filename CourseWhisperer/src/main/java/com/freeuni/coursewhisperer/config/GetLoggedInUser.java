package com.freeuni.coursewhisperer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class GetLoggedInUser {

    @Bean
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof OidcUser) {
                // For OpenID Connect (OAuth2) authentication
                OidcUser oidcUser = (OidcUser) principal;
                return "Current User: " + oidcUser.getPreferredUsername();
            } else if (principal instanceof OAuth2User) {
                // For generic OAuth2 authentication
                OAuth2User oauth2User = (OAuth2User) principal;
                return "Current User: " + oauth2User.getName();
            } else {
                return "Unsupported principal type: " + principal.getClass().getName();
            }
        } else {
            return "User not authenticated";
        }
    }
}
