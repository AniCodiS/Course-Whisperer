package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudyGroupMemberResponse {

    private String groupName;
    private String memberUsername;
    private String errorMessage;

    public StudyGroupMemberResponse() {
    }

    public StudyGroupMemberResponse(String groupName, String memberUsername) {
        this.groupName = groupName;
        this.memberUsername = memberUsername;
    }

    public StudyGroupMemberResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}