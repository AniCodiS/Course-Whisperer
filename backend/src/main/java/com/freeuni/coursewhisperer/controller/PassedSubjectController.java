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

    @PostMapping("/create-subject")
    public ResponseEntity<PassedSubjectDTO> createPassedSubject(@RequestBody @Valid PassedSubjectDTO passedSubjectDTO) {
        var res = passedSubjectMapper.modelToDto(passedSubjectService.
                createPassedSubject(passedSubjectMapper.dtoToModel(passedSubjectDTO)));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/retrieve-subjects")
    public ResponseEntity<List<PassedSubjectDTO>> getPassedSubjects(@RequestParam String username) {
        var res = passedSubjectService.getPassedSubjects(username).stream().map(passedSubjectMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassedSubjectDTO> updatePassedSubject(@PathVariable Long id,
                                                                @RequestBody @Valid PassedSubjectDTO passedSubjectDTO) {
        var res = passedSubjectMapper.modelToDto(passedSubjectService.updatePassedSubject(id, passedSubjectMapper.dtoToModel(passedSubjectDTO)));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deletePassedSubject(@PathVariable Long id, @RequestParam String username) {
        passedSubjectService.deletePassedSubject(username, id);
        return ResponseEntity.ok().build();
    }
}
