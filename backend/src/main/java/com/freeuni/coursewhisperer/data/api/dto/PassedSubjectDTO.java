package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassedSubjectDTO {
    private Long id;
    private String username;
    private String subject;
    private String subjectName;
    private String grade;
    private Integer gradeScore;
}
