package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserScoreDTO {
    private String username;
    private Integer score;
}