package com.freeuni.coursewhisperer.data.api.dto;

import com.freeuni.coursewhisperer.common.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CalculateGPADTO {
    List<Course> courses;
}
