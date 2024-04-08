package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.CalculateGPADTO;
import com.freeuni.coursewhisperer.data.api.dto.CalculateGPAResponse;
import com.freeuni.coursewhisperer.exception.CourseWhispererException;
import com.freeuni.coursewhisperer.service.GPACalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/gpa-calculator")
public class GPACalculatorController {

    private final GPACalculatorService gpaCalculatorService;

    public GPACalculatorController(GPACalculatorService gpaCalculatorService) {
        this.gpaCalculatorService = gpaCalculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculateGPAResponse> calculateGPA(@RequestBody CalculateGPADTO calculateGPADTO) {
        try {
            return ResponseEntity.ok(new CalculateGPAResponse(gpaCalculatorService.calculateGPA(calculateGPADTO)));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new CalculateGPAResponse(e.getErrorDescription()));
        }
    }
}