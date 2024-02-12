package com.freeuni.coursewhisperer.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    private User user;
    private Post post;
    private String content;
}
