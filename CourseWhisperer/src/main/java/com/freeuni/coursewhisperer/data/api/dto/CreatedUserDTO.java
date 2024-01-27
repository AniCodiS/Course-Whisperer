package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatedUserDTO {
    private Long id;
    private String mail;
    private String username;
    private String password;
}