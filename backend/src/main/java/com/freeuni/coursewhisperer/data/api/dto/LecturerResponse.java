package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerResponse {

    private String lecturerName;
    private String department;
    private String email;
    private String errorMessage;

    public LecturerResponse() {
    }

    public LecturerResponse(String lecturerName, String department, String email) {
        this.lecturerName = lecturerName;
        this.department = department;
        this.email = email;
    }

    public LecturerResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
