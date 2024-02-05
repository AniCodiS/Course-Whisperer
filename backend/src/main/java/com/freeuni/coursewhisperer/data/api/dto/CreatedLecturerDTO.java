package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreatedLecturerDTO {
    private Long id;
    private String lecturerName;
    private String department;
    private String email;
}
