package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassedSubjectDTO {
    private String username;
    private String subject;
    private Integer gradeScore;
}
