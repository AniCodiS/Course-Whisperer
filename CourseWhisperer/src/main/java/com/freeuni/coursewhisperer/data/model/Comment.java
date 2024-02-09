package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.entity.PostEntity;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    private UserEntity user;
    private PostEntity post;
    private String content;
}
