package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@Getter
@Setter
public class CreatedUserScoreDTO {

    private Long id;
    private String username;
    private Integer score;
    private String errorMessage;

    public CreatedUserScoreDTO() {
    }

    public CreatedUserScoreDTO(String username, Integer score) {
        this.username = username;
        this.score = score;
    }

    public CreatedUserScoreDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}