package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatedUserScoreDTO {
    private Long id;
    private String username;
    private Integer score;
}