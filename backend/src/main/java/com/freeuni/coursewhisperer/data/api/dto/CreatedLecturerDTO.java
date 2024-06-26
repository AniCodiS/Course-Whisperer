package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@Setter
@Getter
public class CreatedLecturerDTO {

    private Long id;
    private String lecturerName;
    private String department;
    private String email;
    private String errorMessage;

    public CreatedLecturerDTO() {
    }

    public CreatedLecturerDTO(String lecturerName, String department, String email) {
        this.lecturerName = lecturerName;
        this.department = department;
        this.email = email;
    }

    public CreatedLecturerDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
