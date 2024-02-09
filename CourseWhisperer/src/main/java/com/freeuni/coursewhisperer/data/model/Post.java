package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.entity.SubjectEntity;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private UserEntity student;
    private SubjectEntity subject;
    private String content;
}
