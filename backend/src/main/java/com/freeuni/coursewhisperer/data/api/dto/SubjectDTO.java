package com.freeuni.coursewhisperer.data.api.dto;

import com.freeuni.coursewhisperer.data.enums.ESchool;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectDTO {
    private String code;
    private String name;
    private ESchool schoolName;
    private Integer creditScore;
    private String lecturer;
    private ESemester semester;
}
