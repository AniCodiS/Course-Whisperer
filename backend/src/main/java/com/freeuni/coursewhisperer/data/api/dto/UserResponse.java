package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String email;
    private String username;
    private String password;
    private String errorMessage;

    public UserResponse() {
    }

    public UserResponse(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
