package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.SubjectDTO;
import com.freeuni.coursewhisperer.data.mapper.SubjectMapper;
import com.freeuni.coursewhisperer.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    public SubjectController(SubjectService subjectService, SubjectMapper subjectMapper) {
        this.subjectService = subjectService;
        this.subjectMapper = subjectMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody @Valid SubjectDTO subjectDTO) {
        var res = subjectMapper.modelToDto(subjectService.createSubject(subjectDTO));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDTO>> getSubjects() {
        var res = subjectService.getSubjects().stream().map(subjectMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{code}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable String code,
                                                    @RequestBody @Valid SubjectDTO subjectDTO) {
        var res = subjectMapper.modelToDto(subjectService.updateSubject(code, subjectMapper.dtoToModel(subjectDTO)));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/remove/{code}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String code) {
        subjectService.deleteSubject(code);
        return ResponseEntity.ok().build();
    }
}
