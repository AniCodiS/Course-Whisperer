package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.enums.ESchool;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subject {
    private String name;
    private String code;
    private ESchool schoolName;
    private Integer creditScore;
    private String lecturer;
    private ESemester semester;
}
