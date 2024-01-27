package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String mail;
    private String username;
    private String password;
}
