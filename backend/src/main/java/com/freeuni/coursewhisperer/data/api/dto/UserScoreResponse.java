package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserScoreResponse {

    private String username;
    private Integer score;
    private String errorMessage;

    public UserScoreResponse() {
    }

    public UserScoreResponse(String username, Integer score) {
        this.username = username;
        this.score = score;
    }

    public UserScoreResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}