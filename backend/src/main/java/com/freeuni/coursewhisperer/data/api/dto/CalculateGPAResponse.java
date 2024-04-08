package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@Setter
@Getter
public class CalculateGPAResponse {

    private Double gpa;
    private String errorMessage;

    public CalculateGPAResponse(Double aDouble) {
        gpa = aDouble;
    }

    public CalculateGPAResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
