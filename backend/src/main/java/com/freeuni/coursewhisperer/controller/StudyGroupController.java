package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.DeleteStudyGroupDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupDTO;
import com.freeuni.coursewhisperer.service.StudyGroupService;
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
    public List<StudyGroupDTO> getAllStudyGroups() {
        return studyGroupService.getAllStudyGroups();
    }

    @GetMapping("/get/{groupName}")
    public StudyGroupDTO getStudyGroupById(@PathVariable String groupName) {
        return studyGroupService.getStudyGroupByGroupName(groupName);
    }

    @PostMapping("/create")
    public StudyGroupDTO createStudyGroup(@RequestBody StudyGroupDTO studyGroupDTO) {
        return studyGroupService.createStudyGroup(studyGroupDTO);
    }

    @PutMapping("/update/{groupName}")
    public StudyGroupDTO updateStudyGroup(@PathVariable String groupName, @RequestBody StudyGroupDTO studyGroupDTO) {
        return studyGroupService.updateStudyGroup(groupName, studyGroupDTO);
    }

    @DeleteMapping("/delete")
    public void deleteStudyGroup(@RequestBody DeleteStudyGroupDTO deleteStudyGroupDTO) {
        studyGroupService.deleteStudyGroup(deleteStudyGroupDTO);
    }
}
