package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LecturerDTO {
    private String lecturerName;
    private String department;
    private String email;
}
