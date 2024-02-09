package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.entity.SubjectEntity;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import com.freeuni.coursewhisperer.data.enums.EGrade;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PassedSubject {
    private UserEntity student;
    private SubjectEntity subject;
    private EGrade grade;
    private int gradeScore;
}
