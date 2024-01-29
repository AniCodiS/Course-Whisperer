package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.common.Course;
import org.springframework.stereotype.Service;

@Service
public class GPACalculator {

    // Coefficients for grades
    private static final double COEFFICIENT_A = 4.0;
    private static final double COEFFICIENT_B = 20.28 / 6;
    private static final double COEFFICIENT_C = 16.62 / 6;
    private static final double COEFFICIENT_D = 12.96 / 6;
    private static final double COEFFICIENT_E = 9.3 / 6;
    private static final double COEFFICIENT_F = 0.0;

    public static double calculateCourseGradePoints(Course c) {
        int creditHours = c.getCreditHours();
        String grade = c.getGrade();
        return switch (grade) {
            case "A" -> COEFFICIENT_A * creditHours;
            case "B" -> COEFFICIENT_B * creditHours;
            case "C" -> COEFFICIENT_C * creditHours;
            case "D" -> COEFFICIENT_D * creditHours;
            case "E" -> COEFFICIENT_E * creditHours;
            case "F" -> COEFFICIENT_F * creditHours;
            default -> 0.0; // Handle unknown grades
        };
    }
}
