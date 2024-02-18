package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudyGroupMember {
    private StudyGroup studyGroup;
    private UserEntity member;
}
