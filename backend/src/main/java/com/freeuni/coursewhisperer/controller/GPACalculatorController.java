package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.CalculateGPADTO;
import com.freeuni.coursewhisperer.service.GPACalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gpa-calculator")
public class GPACalculatorController {

    private final GPACalculatorService gpaCalculatorService;

    public GPACalculatorController(GPACalculatorService gpaCalculatorService) {
        this.gpaCalculatorService = gpaCalculatorService;
    }

    @GetMapping
    public Double calculateGPA(@RequestBody CalculateGPADTO calculateGPADTO) {
        return gpaCalculatorService.calculateGPA(calculateGPADTO);
    }
}