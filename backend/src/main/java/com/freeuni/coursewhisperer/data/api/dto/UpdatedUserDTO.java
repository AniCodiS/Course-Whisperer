package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedUserDTO {

    private String email;
    private String username;
    private String errorMessage;

    public UpdatedUserDTO() {
    }

    public UpdatedUserDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public UpdatedUserDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}