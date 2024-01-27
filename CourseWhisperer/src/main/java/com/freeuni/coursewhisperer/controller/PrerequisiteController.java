package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.PrerequisiteDTO;
import com.freeuni.coursewhisperer.data.entity.Prerequisite;
import com.freeuni.coursewhisperer.service.PrerequisiteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prerequisites")
public class PrerequisiteController {

    private final PrerequisiteService prerequisiteService;

    public PrerequisiteController(PrerequisiteService prerequisiteService) {
        this.prerequisiteService = prerequisiteService;
    }

    @GetMapping
    public List<PrerequisiteDTO> getAllPrerequisites() {
        return prerequisiteService.getAllPrerequisites();
    }

    @GetMapping("/{subjectName}")
    public PrerequisiteDTO getPrerequisiteById(@PathVariable String subjectName) {
        return prerequisiteService.getPrerequisiteBySubjectName(subjectName);
    }

    @PostMapping
    public PrerequisiteDTO createPrerequisite(@RequestBody PrerequisiteDTO prerequisiteDTO) {
        return prerequisiteService.createPrerequisite(prerequisiteDTO);
    }

    @PutMapping("/{subjectName}")
    public PrerequisiteDTO updatePrerequisite(@PathVariable String subjectName, @RequestBody PrerequisiteDTO prerequisiteDTO) {
        return prerequisiteService.updatePrerequisite(subjectName, prerequisiteDTO);
    }

    @DeleteMapping("/{subjectName}")
    public void deletePrerequisite(@PathVariable String subjectName) {
        prerequisiteService.deletePrerequisite(subjectName);
    }
}
