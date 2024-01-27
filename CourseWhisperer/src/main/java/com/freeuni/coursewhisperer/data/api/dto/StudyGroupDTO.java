package com.freeuni.coursewhisperer.data.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudyGroupDTO {
    private String subjectName;
    private LocalDateTime meetingTime;
    private String groupName;
}