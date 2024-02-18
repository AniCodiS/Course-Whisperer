package com.freeuni.coursewhisperer.data.api.dto;

import com.freeuni.coursewhisperer.data.enums.EGrade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassedSubjectDTO {
    private String student;
    private String subject;
    private EGrade grade;
    private Integer gradeScore;
}
