package com.freeuni.coursewhisperer;

import com.freeuni.coursewhisperer.common.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.freeuni.coursewhisperer.service.GPACalculator.calculateGPA;

public class TestGPACalculatorService {

    @Test
    public void testCalculateCourseGradePoints() {
        // Test case for GPA calculation
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("D", 4));
        courses.add(new Course("D", 5));
        courses.add(new Course("E", 6));
        courses.add(new Course("B", 4));
        courses.add(new Course("C", 4));
        courses.add(new Course("C", 3));
        courses.add(new Course("D", 6));
        courses.add(new Course("A", 4));
        courses.add(new Course("C", 2));
        courses.add(new Course("C", 5));
        courses.add(new Course("B", 6));
        courses.add(new Course("A", 6));
        courses.add(new Course("A", 5));
        courses.add(new Course("A", 3));
        courses.add(new Course("C", 6));
        courses.add(new Course("C", 4));
        courses.add(new Course("A", 8));
        courses.add(new Course("A", 5));
        courses.add(new Course("A", 3));
        courses.add(new Course("D", 6));
        courses.add(new Course("D", 6));
        courses.add(new Course("E", 6));
        courses.add(new Course("A", 3));
        courses.add(new Course("C", 4));
        courses.add(new Course("B", 4));
        courses.add(new Course("D", 6));
        courses.add(new Course("D", 6));
        courses.add(new Course("A", 6));
        courses.add(new Course("A", 6));
        courses.add(new Course("C", 6));
        courses.add(new Course("D", 6));
        courses.add(new Course("E", 6));
        courses.add(new Course("E", 6));
        courses.add(new Course("A", 6));
        courses.add(new Course("B", 6));
        courses.add(new Course("C", 4));
        courses.add(new Course("C", 6));
        courses.add(new Course("E", 6));
        courses.add(new Course("B", 6));
        courses.add(new Course("D", 6));
        courses.add(new Course("E", 6));
        courses.add(new Course("E", 6));
        courses.add(new Course("C", 3));
        courses.add(new Course("A", 6));

        calculateGPA(courses);
    }
}
