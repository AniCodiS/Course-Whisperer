package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.enums.EPostType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private User student;
    private Subject subject;
    private String content;
    private Integer upVote;
    private Integer downVote;
    private EPostType type;
}
