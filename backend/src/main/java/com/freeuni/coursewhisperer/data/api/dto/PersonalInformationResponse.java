package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalInformationResponse {

    private String firstName;
    private String lastName;
    private Integer year;
    private String faculty;
    private String email;
    private String errorMessage;

    public PersonalInformationResponse() {
    }

    public PersonalInformationResponse(String firstName, String lastName, Integer year, String faculty, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.faculty = faculty;
        this.email = email;
    }

    public PersonalInformationResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
