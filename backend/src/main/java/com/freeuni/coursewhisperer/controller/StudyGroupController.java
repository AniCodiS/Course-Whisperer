package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.DeleteStudyGroupDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupResponse;
import com.freeuni.coursewhisperer.exception.CourseWhispererException;
import com.freeuni.coursewhisperer.service.StudyGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-group")
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    public StudyGroupController(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudyGroupResponse>> getAllStudyGroups() {
        try {
            return ResponseEntity.ok().body(studyGroupService.getAllStudyGroups());
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(List.of(new StudyGroupResponse(e.getErrorDescription())));
        }
    }

    @GetMapping("/get/{groupName}")
    public ResponseEntity<StudyGroupResponse> getStudyGroupById(@PathVariable String groupName) {
        try {
            return ResponseEntity.ok().body(studyGroupService.getStudyGroupByGroupName(groupName));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new StudyGroupResponse(e.getErrorDescription()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<StudyGroupResponse> createStudyGroup(@RequestBody StudyGroupDTO studyGroupDTO) {
        try {
            return ResponseEntity.ok().body(studyGroupService.createStudyGroup(studyGroupDTO));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new StudyGroupResponse(e.getErrorDescription()));
        }
    }

    @PutMapping("/update/{groupName}")
    public ResponseEntity<StudyGroupResponse> updateStudyGroup(@PathVariable String groupName, @RequestBody StudyGroupDTO studyGroupDTO) {
        try {
            return ResponseEntity.ok().body(studyGroupService.updateStudyGroup(groupName, studyGroupDTO));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new StudyGroupResponse(e.getErrorDescription()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudyGroup(@RequestBody DeleteStudyGroupDTO deleteStudyGroupDTO) {
        try {
            studyGroupService.deleteStudyGroup(deleteStudyGroupDTO);
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getErrorDescription());
        }
        return ResponseEntity.ok().body("Study Group deleted successfully");
    }
}
