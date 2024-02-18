package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerDTOResponse {

    private String lecturerName;
    private String department;
    private String email;
    private String errorMessage;

    public LecturerDTOResponse() {
    }

    public LecturerDTOResponse(String lecturerName, String department, String email) {
        this.lecturerName = lecturerName;
        this.department = department;
        this.email = email;
    }

    public LecturerDTOResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
