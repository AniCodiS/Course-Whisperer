package com.freeuni.coursewhisperer.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Prerequisite {
    private Subject subject;
    private Subject prerequisite;
}
