package com.freeuni.coursewhisperer.data.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrerequisiteDTO {
    private String subject;
    private String prerequisite;
}
