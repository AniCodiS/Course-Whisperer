package com.freeuni.coursewhisperer.data.model;

import lombok.*;

@Data
@Builder
public class Lecturer {
    private String lecturerName;
    private String department;
    private String email;
}
