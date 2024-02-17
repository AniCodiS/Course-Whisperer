package com.freeuni.coursewhisperer.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String email;
    private String username;
    private String password;
}
