package com.freeuni.coursewhisperer.data.model;

import com.freeuni.coursewhisperer.data.entity.LecturerEntity;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subject {
    private String name;
    private int creditScore;
    private LecturerEntity lecturer;
    private ESemester semester;
}
