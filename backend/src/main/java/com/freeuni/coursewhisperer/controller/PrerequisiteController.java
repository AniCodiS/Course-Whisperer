package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.PrerequisiteDTO;
import com.freeuni.coursewhisperer.data.mapper.PrerequisiteMapper;
import com.freeuni.coursewhisperer.service.PrerequisiteService;
import org.springframework.http.ResponseEntity;
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

    private final PrerequisiteMapper prerequisiteMapper;

    public PrerequisiteController(PrerequisiteService prerequisiteService,
                                  PrerequisiteMapper prerequisiteMapper) {
        this.prerequisiteService = prerequisiteService;
        this.prerequisiteMapper = prerequisiteMapper;
    }

    @GetMapping
    public List<PrerequisiteDTO> getAllPrerequisites() {
        return prerequisiteMapper.modelToDto(prerequisiteService.getAllPrerequisites());
    }

    @GetMapping("/{id}")
    public PrerequisiteDTO getPrerequisiteById(@PathVariable Long id) {
        return prerequisiteService.getPrerequisiteById(id);
    }

    @PostMapping
    public PrerequisiteDTO createPrerequisite(@RequestBody PrerequisiteDTO prerequisiteDTO) {
        return prerequisiteService.createPrerequisite(prerequisiteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePrerequisite(@PathVariable Long id, @RequestBody PrerequisiteDTO prerequisiteDTO) {
        prerequisiteService.updatePrerequisite(id, prerequisiteDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deletePrerequisite(@PathVariable Long id) {
        prerequisiteService.deletePrerequisite(id);
    }
}
