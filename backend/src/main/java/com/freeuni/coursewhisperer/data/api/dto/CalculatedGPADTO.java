package com.freeuni.coursewhisperer.data.api.dto;

import lombok.*;

@Setter
@Getter
public class CalculatedGPADTO {

    private Double gpa;
    private String errorMessage;

    public CalculatedGPADTO(Double aDouble) {
    }

    public CalculatedGPADTO(String errorMessage) {
    }
}
