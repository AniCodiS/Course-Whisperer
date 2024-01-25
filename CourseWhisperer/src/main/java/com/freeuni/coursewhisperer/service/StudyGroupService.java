package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.model.db.StudyGroup;
import com.freeuni.coursewhisperer.repository.StudyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;

    @Autowired
    public StudyGroupService(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    public List<StudyGroup> getAllStudyGroups() {
        return studyGroupRepository.findAll();
    }

    public StudyGroup getStudyGroupById(Long id) {
        return studyGroupRepository.findById(id).orElse(null);
    }

    public StudyGroup createStudyGroup(StudyGroup studyGroup) {
        return studyGroupRepository.save(studyGroup);
    }

    public StudyGroup updateStudyGroup(Long id, StudyGroup studyGroup) {
        if (studyGroupRepository.existsById(id)) {
            studyGroup.setId(id);
            return studyGroupRepository.save(studyGroup);
        }
        return null;
    }

    public void deleteStudyGroup(Long id) {
        studyGroupRepository.deleteById(id);
    }
}
