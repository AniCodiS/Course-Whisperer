package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.DeleteStudyGroupDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupDTO;
import com.freeuni.coursewhisperer.data.entity.StudyGroupEntity;
import com.freeuni.coursewhisperer.data.mapper.StudyGroupMapper;
import com.freeuni.coursewhisperer.repository.StudyGroupRepository;
import jakarta.transaction.Transactional;
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
        List<StudyGroupEntity> studyGroups = studyGroupRepository.findAll();
        if (studyGroups.isEmpty()) {
            // TODO: throw exception
            return null;
        }
        List<StudyGroupDTO> studyGroupDTOs = new ArrayList<>();
        for (StudyGroupEntity studyGroup : studyGroups) {
            studyGroupDTOs.add(mapper.modelToDto(mapper.entityToModel(studyGroup)));
        }
        return studyGroupDTOs;
    }

    public StudyGroupDTO getStudyGroupByGroupName(String groupName) {
        if (!studyGroupRepository.existsByGroupName(groupName)) {
            return mapper.modelToDto(mapper.entityToModel(studyGroupRepository.findByGroupName(groupName)));
        }
        // TODO: throw exception
        return null;
    }

    public StudyGroupDTO createStudyGroup(StudyGroupDTO studyGroupDTO) {
        if (studyGroupRepository.existsByGroupName(studyGroupDTO.getGroupName())) {
            // TODO: throw exception
            return null;
        }
        studyGroupDTO.setCurrentMemberCount(0);
        studyGroupDTO.setMaxMemberCount(10);
        StudyGroupEntity studyGroupEntity = mapper.modelToEntity(mapper.dtoToModel(studyGroupDTO));
        StudyGroupEntity savedStudyGroupEntity = studyGroupRepository.save(studyGroupEntity);
        return mapper.modelToDto(mapper.entityToModel(savedStudyGroupEntity));
    }

    public StudyGroupDTO updateStudyGroup(String groupName, StudyGroupDTO studyGroupDTO) {
        if (studyGroupRepository.existsByGroupName(groupName)) {
            StudyGroupEntity studyGroupEntity = mapper.modelToEntity(mapper.dtoToModel(studyGroupDTO));
            studyGroupEntity.setId(studyGroupRepository.findByGroupName(groupName).getId());
            return mapper.modelToDto(mapper.entityToModel(studyGroupRepository.save(studyGroupEntity)));
        }
        // TODO: throw exception
        return null;
    }

    @Transactional
    public void deleteStudyGroup(DeleteStudyGroupDTO deleteStudyGroupDTO) {
        String creatorUsername = deleteStudyGroupDTO.getCreatorUsername();
        String groupName = deleteStudyGroupDTO.getGroupName();
        if (studyGroupRepository.existsByGroupName(groupName)) {
            StudyGroupEntity studyGroupEntity = studyGroupRepository.findByGroupName(groupName);
            if (studyGroupEntity.getCreatorUsername().equals(creatorUsername)) {
                studyGroupRepository.delete(studyGroupEntity);
            } else {
                // TODO: throw exception
            }
        } else {
            // TODO: throw exception
        }
    }
}
