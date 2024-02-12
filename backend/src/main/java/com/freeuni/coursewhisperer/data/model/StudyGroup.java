package com.freeuni.coursewhisperer.data.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudyGroup {
    private String subjectName;
    private LocalDateTime meetingTime;
    private String groupName;
}
