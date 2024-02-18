package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.DeleteStudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.entity.StudyGroupMemberEntity;
import com.freeuni.coursewhisperer.service.StudyGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-group-members")
public class StudyGroupMemberController {

    private final StudyGroupMemberService studyGroupMemberService;

    @Autowired
    public StudyGroupMemberController(StudyGroupMemberService studyGroupMemberService) {
        this.studyGroupMemberService = studyGroupMemberService;
    }

    @GetMapping
    public List<StudyGroupMemberDTO> getAllStudyGroupMembers() {
        return studyGroupMemberService.getAllStudyGroupMembers();
    }

    @GetMapping("/{id}")
    public StudyGroupMemberEntity getStudyGroupMemberById(@PathVariable Long id) {
        return studyGroupMemberService.getStudyGroupMemberById(id);
    }

    @PostMapping
    public StudyGroupMemberDTO createStudyGroupMember(@RequestBody StudyGroupMemberDTO studyGroupMemberDTO) {
        return studyGroupMemberService.createStudyGroupMember(studyGroupMemberDTO);
    }

    @PutMapping("/{id}")
    public StudyGroupMemberEntity updateStudyGroupMember(@PathVariable Long id, @RequestBody StudyGroupMemberEntity studyGroupMember) {
        return studyGroupMemberService.updateStudyGroupMember(id, studyGroupMember);
    }

    @DeleteMapping
    public void deleteStudyGroupMember(@RequestBody DeleteStudyGroupMemberDTO deleteStudyGroupMemberDTO) {
        studyGroupMemberService.deleteStudyGroupMember(deleteStudyGroupMemberDTO);
    }
}
