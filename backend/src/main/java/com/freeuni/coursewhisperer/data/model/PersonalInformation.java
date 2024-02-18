package com.freeuni.coursewhisperer.data.model;

import lombok.*;

@Data
@Builder
public class PersonalInformation {
    private User user;
    private String firstName;
    private String lastName;
    private Integer year;
    private String faculty;
    private String email;
}
