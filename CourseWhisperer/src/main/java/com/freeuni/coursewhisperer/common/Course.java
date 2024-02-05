package com.freeuni.coursewhisperer.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Course {
    private String grade;
    private int creditHours;
}