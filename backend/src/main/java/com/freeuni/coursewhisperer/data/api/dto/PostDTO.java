package com.freeuni.coursewhisperer.data.api.dto;

import com.freeuni.coursewhisperer.data.enums.EPostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String username;
    private String subject;
    private String content;
    private Integer upVote;
    private Integer downVote;
    private EPostType type;
}
