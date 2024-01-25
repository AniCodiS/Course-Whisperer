package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.model.db.StudyGroupMember;
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
    public List<StudyGroupMember> getAllStudyGroupMembers() {
        return studyGroupMemberService.getAllStudyGroupMembers();
    }

    @GetMapping("/{id}")
    public StudyGroupMember getStudyGroupMemberById(@PathVariable Long id) {
        return studyGroupMemberService.getStudyGroupMemberById(id);
    }

    @PostMapping
    public StudyGroupMember createStudyGroupMember(@RequestBody StudyGroupMember studyGroupMember) {
        return studyGroupMemberService.createStudyGroupMember(studyGroupMember);
    }

    @PutMapping("/{id}")
    public StudyGroupMember updateStudyGroupMember(@PathVariable Long id, @RequestBody StudyGroupMember studyGroupMember) {
        return studyGroupMemberService.updateStudyGroupMember(id, studyGroupMember);
    }

    @DeleteMapping("/{id}")
    public void deleteStudyGroupMember(@PathVariable Long id) {
        studyGroupMemberService.deleteStudyGroupMember(id);
    }
}
