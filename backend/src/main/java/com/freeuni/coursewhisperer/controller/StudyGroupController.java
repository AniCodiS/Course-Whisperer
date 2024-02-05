package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.StudyGroupDTO;
import com.freeuni.coursewhisperer.data.entity.StudyGroup;
import com.freeuni.coursewhisperer.service.StudyGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-groups")
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    public StudyGroupController(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    @GetMapping
    public List<StudyGroupDTO> getAllStudyGroups() {
        return studyGroupService.getAllStudyGroups();
    }

    @GetMapping("/{groupName}")
    public StudyGroupDTO getStudyGroupById(@PathVariable String groupName) {
        return studyGroupService.getStudyGroupByGroupName(groupName);
    }

    @PostMapping
    public StudyGroupDTO createStudyGroup(@RequestBody StudyGroupDTO studyGroupDTO) {
        return studyGroupService.createStudyGroup(studyGroupDTO);
    }

    @PutMapping("/{groupName}")
    public StudyGroupDTO updateStudyGroup(@PathVariable String groupName, @RequestBody StudyGroupDTO studyGroupDTO) {
        return studyGroupService.updateStudyGroup(groupName, studyGroupDTO);
    }

    @DeleteMapping("/{groupName}")
    public void deleteStudyGroup(@PathVariable String groupName) {
        studyGroupService.deleteStudyGroup(groupName);
    }
}
