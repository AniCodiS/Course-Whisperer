package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.PassedSubjectDTO;
import com.freeuni.coursewhisperer.data.entity.PassedSubjectEntity;
import com.freeuni.coursewhisperer.data.mapper.PassedSubjectMapper;
import com.freeuni.coursewhisperer.data.model.PassedSubject;
import com.freeuni.coursewhisperer.service.PassedSubjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passedSubject")
public class PassedSubjectController extends AbstractController<PassedSubjectEntity, Long, PassedSubject, PassedSubjectDTO> {

    private final PassedSubjectService passedSubjectService;
    private final PassedSubjectMapper passedSubjectMapper;

    public PassedSubjectController(PassedSubjectService passedSubjectService,
                                   PassedSubjectMapper passedSubjectMapper) {
        super(passedSubjectService, passedSubjectMapper);
        this.passedSubjectService = passedSubjectService;
        this.passedSubjectMapper = passedSubjectMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<PassedSubjectDTO> createPassedSubject(@RequestBody @Valid PassedSubjectDTO passedSubjectDTO) {
        var res = passedSubjectMapper.modelToDto(passedSubjectService.createPassedSubject(passedSubjectDTO));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PassedSubjectDTO>> getPassedSubjects() {
        var res = passedSubjectService.getPassedSubjects().stream().map(passedSubjectMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{code}")
    public ResponseEntity<PassedSubjectDTO> updatePassedSubject(@PathVariable String code,
                                                                @RequestBody @Valid PassedSubjectDTO passedSubjectDTO) {
        var res = passedSubjectMapper.modelToDto(passedSubjectService.updatePassedSubject(code, passedSubjectMapper.dtoToModel(passedSubjectDTO)));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/remove/{code}")
    public ResponseEntity<Void> deletePassedSubject(@PathVariable String code, @RequestParam String username) {
        passedSubjectService.deletePassedSubject(username, code);
        return ResponseEntity.ok().build();
    }
}
