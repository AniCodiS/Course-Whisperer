package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.PrerequisiteDTO;
import com.freeuni.coursewhisperer.data.mapper.PrerequisiteMapper;
import com.freeuni.coursewhisperer.service.PrerequisiteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/{subject}")
    public List<PrerequisiteDTO> getPrerequisiteById(@PathVariable String subject) {
        return prerequisiteService.getPrerequisiteBySubject(subject).stream().map(prerequisiteMapper::modelToDto).toList();
    }

//    @PostMapping
//    public PrerequisiteDTO createPrerequisite(@RequestBody PrerequisiteDTO prerequisiteDTO) {
//        return prerequisiteService.createPrerequisite(prerequisiteDTO);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updatePrerequisite(@PathVariable Long id, @RequestBody PrerequisiteDTO prerequisiteDTO) {
//        prerequisiteService.updatePrerequisite(id, prerequisiteDTO);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletePrerequisite(@PathVariable Long id) {
//        prerequisiteService.deletePrerequisite(id);
//    }
}
