package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.SubjectDTO;
import com.freeuni.coursewhisperer.data.enums.ESchool;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import com.freeuni.coursewhisperer.data.mapper.SubjectMapper;
import com.freeuni.coursewhisperer.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    public SubjectController(SubjectService subjectService, SubjectMapper subjectMapper) {
        this.subjectService = subjectService;
        this.subjectMapper = subjectMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        var res = subjectService.getAllSubjects().stream().map(subjectMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/create")
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody @Valid SubjectDTO subjectDTO) {
        var res = subjectMapper.modelToDto(subjectService.createSubject(subjectMapper.dtoToModel(subjectDTO)));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SubjectDTO>> getSubjects(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String code,
                                                        @RequestParam(required = false) ESchool schoolName,
                                                        @RequestParam(required = false) Integer creditScore,
                                                        @RequestParam(required = false) Long lecturer,
                                                        @RequestParam(required = false) ESemester semester) {
        var res = subjectService.search(name, code, schoolName, creditScore, lecturer, semester).stream().map(subjectMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/choose")
    public ResponseEntity<List<SubjectDTO>> chooseSubjects(@RequestParam(required = false) String username,
                                                           @RequestParam(required = false) ESchool schoolName,
                                                           @RequestParam(required = false) Integer creditScore,
                                                           @RequestParam(required = false) ESemester semester) {
        var res = subjectService.chooseSubject(username, schoolName, creditScore, semester).stream().map(subjectMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{code}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable String code,
                                                    @RequestBody @Valid SubjectDTO subjectDTO) {
        var res = subjectMapper.modelToDto(subjectService.updateSubject(code, subjectMapper.dtoToModel(subjectDTO)));
        return ResponseEntity.ok(res);
    }
//    AppData\Roaming\JetBrains\IntelliJIdea2023.1\node\node-v18.15.0-win-x64\node.exe

    @DeleteMapping("/remove/{code}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String code) {
        subjectService.deleteSubject(code);
        return ResponseEntity.ok().build();
    }
}
