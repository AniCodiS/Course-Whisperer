package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.model.Prerequisite;
import com.freeuni.coursewhisperer.service.PrerequisiteService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PrerequisiteController(PrerequisiteService prerequisiteService) {
        this.prerequisiteService = prerequisiteService;
    }

    @GetMapping
    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteService.getAllPrerequisites();
    }

    @GetMapping("/{id}")
    public Prerequisite getPrerequisiteById(@PathVariable Long id) {
        return prerequisiteService.getPrerequisiteById(id);
    }

    @PostMapping
    public Prerequisite createPrerequisite(@RequestBody Prerequisite prerequisite) {
        return prerequisiteService.createPrerequisite(prerequisite);
    }

    @PutMapping("/{id}")
    public Prerequisite updatePrerequisite(@PathVariable Long id, @RequestBody Prerequisite prerequisite) {
        return prerequisiteService.updatePrerequisite(id, prerequisite);
    }

    @DeleteMapping("/{id}")
    public void deletePrerequisite(@PathVariable Long id) {
        prerequisiteService.deletePrerequisite(id);
    }
}
