package com.freeuni.coursewhisperer.data.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdatePersonalInformationDTO {
    private String firstName;
    private String lastName;
    private Integer year;
    private String faculty;
}
