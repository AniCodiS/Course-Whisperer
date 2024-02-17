package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.entity.StudyGroupEntity;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudyGroupMember {
    private StudyGroupEntity studyGroup;
    private UserEntity member;
}
