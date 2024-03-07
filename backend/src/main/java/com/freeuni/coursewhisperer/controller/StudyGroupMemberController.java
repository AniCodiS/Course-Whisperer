package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.DeleteStudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberResponse;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupResponse;
import com.freeuni.coursewhisperer.data.entity.StudyGroupMemberEntity;
import com.freeuni.coursewhisperer.exception.CourseWhispererException;
import com.freeuni.coursewhisperer.service.StudyGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-group-member")
public class StudyGroupMemberController {

    private final StudyGroupMemberService studyGroupMemberService;

    @Autowired
    public StudyGroupMemberController(StudyGroupMemberService studyGroupMemberService) {
        this.studyGroupMemberService = studyGroupMemberService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudyGroupMemberResponse>> getAllStudyGroupMembers() {
        try {
            return ResponseEntity.ok().body(studyGroupMemberService.getAllStudyGroupMembers());
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(List.of(new StudyGroupMemberResponse(e.getErrorDescription())));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<StudyGroupMemberResponse> createStudyGroupMember(@RequestBody StudyGroupMemberDTO studyGroupMemberDTO) {
        try {
            return ResponseEntity.ok().body(studyGroupMemberService.createStudyGroupMember(studyGroupMemberDTO));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new StudyGroupMemberResponse(e.getErrorDescription()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudyGroupMember(@RequestBody DeleteStudyGroupMemberDTO deleteStudyGroupMemberDTO) {
        try {
            studyGroupMemberService.deleteStudyGroupMember(deleteStudyGroupMemberDTO);
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getErrorDescription());
        }
        return ResponseEntity.ok().body("User from study group deleted successfully");
    }
}
