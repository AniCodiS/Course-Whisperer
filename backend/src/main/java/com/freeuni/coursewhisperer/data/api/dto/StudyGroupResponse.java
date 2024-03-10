package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class StudyGroupResponse {

    private String subjectName;
    private LocalDateTime meetingTime;
    private String groupName;
    private Integer currentMemberCount;
    private Integer maxMemberCount;
    private String errorMessage;

    public StudyGroupResponse() {
    }

    public StudyGroupResponse(String subjectName, LocalDateTime meetingTime, String groupName, Integer currentMemberCount, Integer maxMemberCount) {
        this.subjectName = subjectName;
        this.meetingTime = meetingTime;
        this.groupName = groupName;
        this.currentMemberCount = currentMemberCount;
        this.maxMemberCount = maxMemberCount;
    }

    public StudyGroupResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}