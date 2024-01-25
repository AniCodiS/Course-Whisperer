package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.model.db.StudyGroup;
import com.freeuni.coursewhisperer.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/study-groups")
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    @Autowired
    public StudyGroupController(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    @GetMapping
    public List<StudyGroup> getAllStudyGroups() {
        return studyGroupService.getAllStudyGroups();
    }

    @GetMapping("/{id}")
    public StudyGroup getStudyGroupById(@PathVariable Long id) {
        return studyGroupService.getStudyGroupById(id);
    }

    @PostMapping
    public StudyGroup createStudyGroup(@RequestBody StudyGroup studyGroup) {
        return studyGroupService.createStudyGroup(studyGroup);
    }

    @PutMapping("/{id}")
    public StudyGroup updateStudyGroup(@PathVariable Long id, @RequestBody StudyGroup studyGroup) {
        return studyGroupService.updateStudyGroup(id, studyGroup);
    }

    @DeleteMapping("/{id}")
    public void deleteStudyGroup(@PathVariable Long id) {
        studyGroupService.deleteStudyGroup(id);
    }
}
