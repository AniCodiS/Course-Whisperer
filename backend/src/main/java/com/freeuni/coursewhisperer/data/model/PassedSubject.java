package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.enums.EGrade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassedSubject {
    private Long id;
    private String username;
    private String subject;
    private EGrade grade;
    private Integer gradeScore;
}
