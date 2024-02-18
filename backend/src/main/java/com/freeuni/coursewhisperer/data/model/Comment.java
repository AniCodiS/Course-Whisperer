package com.freeuni.coursewhisperer.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    private Long id;
    private String username;
    private Post post;
    private String content;
}
