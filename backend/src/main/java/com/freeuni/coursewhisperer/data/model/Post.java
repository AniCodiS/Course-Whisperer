package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.enums.EPostType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private Long id;
    private String username;
    private String subject;
    private String content;
    private Integer upVote;
    private String upVoters;
    private String downVoters;
    private Integer downVote;
    private EPostType type;
}
