package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.common.Course;
import com.freeuni.coursewhisperer.data.api.dto.CalculateGPADTO;
import com.freeuni.coursewhisperer.data.entity.SubjectEntity;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GPACalculatorService {

    private SubjectRepository subjectRepository;

    public GPACalculatorService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    // Coefficients for grades
    private static final double COEFFICIENT_A = 4.0;
    private static final double COEFFICIENT_B = 20.28 / 6;
    private static final double COEFFICIENT_C = 16.62 / 6;
    private static final double COEFFICIENT_D = 12.96 / 6;
    private static final double COEFFICIENT_E = 9.3 / 6;
    private static final double COEFFICIENT_F = 0.0;

    public Double calculateGPA(CalculateGPADTO calculateGPADTO) {
        List<Course> courses = calculateGPADTO.getCourses();
        if (courses == null || courses.isEmpty()) {
            throw ExceptionFactory.GPACoursesEmpty();
        }
        Double totalGradePoints = 0.0;
        int totalCreditHours = 0;
        // Iterate through each course
        for (Course course : courses) {
            SubjectEntity subject = subjectRepository.findByName(course.getSubjectName());
            int creditHours = subject.getCreditScore();
            String grade = course.getGrade();
            totalGradePoints += calculateCourseGradePoints(creditHours, grade);
            totalCreditHours += creditHours;
        }
        return totalCreditHours > 0 ? totalGradePoints / totalCreditHours : 0.0;
    }

    public Double calculateCourseGradePoints(int creditHours, String grade) {
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
