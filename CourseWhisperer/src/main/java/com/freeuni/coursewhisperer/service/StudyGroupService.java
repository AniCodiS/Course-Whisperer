package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.StudyGroupDTO;
import com.freeuni.coursewhisperer.data.entity.StudyGroup;
import com.freeuni.coursewhisperer.data.mapper.StudyGroupMapper;
import com.freeuni.coursewhisperer.repository.StudyGroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;

    private final StudyGroupMapper mapper;

    public StudyGroupService(StudyGroupRepository studyGroupRepository, StudyGroupMapper mapper) {
        this.studyGroupRepository = studyGroupRepository;
        this.mapper = mapper;
    }

    public List<StudyGroupDTO> getAllStudyGroups() {
        List<StudyGroup> studyGroups = studyGroupRepository.findAll();
        List<StudyGroupDTO> studyGroupDTOs = new ArrayList<>();
        for (StudyGroup studyGroup : studyGroups) {
            studyGroupDTOs.add(mapper.modelToDto(studyGroup));
        }
        return studyGroupDTOs;
    }

    public StudyGroupDTO getStudyGroupByGroupName(String groupName) {
        return mapper.modelToDto(studyGroupRepository.findByGroupName(groupName));
    }

    public StudyGroupDTO createStudyGroup(StudyGroupDTO studyGroupDTO) {
        return mapper.modelToDto(studyGroupRepository.save(mapper.dtoToModel(studyGroupDTO)));
    }

    public StudyGroupDTO updateStudyGroup(String groupName, StudyGroupDTO studyGroupDTO) {
        if (studyGroupRepository.existsByGroupName(groupName)) {
            StudyGroup studyGroup = mapper.dtoToModel(studyGroupDTO);
            studyGroup.setId(studyGroupRepository.findByGroupName(groupName).getId());
            return mapper.modelToDto(studyGroupRepository.save(studyGroup));
        }
        return null;
    }

    public void deleteStudyGroup(String groupName) {
        studyGroupRepository.deleteByGroupName(groupName);
    }
}
