package com.freeuni.coursewhisperer.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserScore {
    private String username;
    private int score;
}
