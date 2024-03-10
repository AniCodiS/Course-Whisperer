package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.DeleteStudyGroupDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupResponse;
import com.freeuni.coursewhisperer.data.entity.StudyGroupEntity;
import com.freeuni.coursewhisperer.data.mapper.StudyGroupMapper;
import com.freeuni.coursewhisperer.data.mapper.StudyGroupResponseMapper;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.StudyGroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;

    private final StudyGroupMapper mapper;

    private final StudyGroupResponseMapper studyGroupResponseMapper;

    public StudyGroupService(StudyGroupRepository studyGroupRepository, StudyGroupMapper mapper, StudyGroupResponseMapper studyGroupResponseMapper) {
        this.studyGroupRepository = studyGroupRepository;
        this.mapper = mapper;
        this.studyGroupResponseMapper = studyGroupResponseMapper;
    }

    public List<StudyGroupResponse> getAllStudyGroups() {
        List<StudyGroupEntity> studyGroups = studyGroupRepository.findAll();
        if (studyGroups.isEmpty()) {
            throw ExceptionFactory.NoStudyGroupsPresent();
        }
        List<StudyGroupResponse> studyGroupResponses = new ArrayList<>();
        for (StudyGroupEntity studyGroup : studyGroups) {
            studyGroupResponses.add(studyGroupResponseMapper.modelToDto(studyGroupResponseMapper.entityToModel(studyGroup)));
        }
        return studyGroupResponses;
    }

    public StudyGroupResponse getStudyGroupByGroupName(String groupName) {
        if (!studyGroupRepository.existsByGroupName(groupName)) {
            return studyGroupResponseMapper.modelToDto(studyGroupResponseMapper.entityToModel(studyGroupRepository.findByGroupName(groupName)));
        }
        throw ExceptionFactory.StudyGroupNotFound();
    }

    public StudyGroupResponse createStudyGroup(StudyGroupDTO studyGroupDTO) {
        if (studyGroupRepository.existsByGroupName(studyGroupDTO.getGroupName())) {
            throw ExceptionFactory.StudyGroupAlreadyExists();
        }
        studyGroupDTO.setCurrentMemberCount(0);
        studyGroupDTO.setMaxMemberCount(10);
        StudyGroupEntity studyGroupEntity = mapper.modelToEntity(mapper.dtoToModel(studyGroupDTO));
        StudyGroupEntity savedStudyGroupEntity = studyGroupRepository.save(studyGroupEntity);
        return studyGroupResponseMapper.modelToDto(studyGroupResponseMapper.entityToModel(savedStudyGroupEntity));
    }

    public StudyGroupResponse updateStudyGroup(String groupName, StudyGroupDTO studyGroupDTO) {
        if (studyGroupRepository.existsByGroupName(groupName)) {
            StudyGroupEntity studyGroupEntity = mapper.modelToEntity(mapper.dtoToModel(studyGroupDTO));
            studyGroupEntity.setId(studyGroupRepository.findByGroupName(groupName).getId());
            return studyGroupResponseMapper.modelToDto(studyGroupResponseMapper.entityToModel(studyGroupRepository.save(studyGroupEntity)));
        }
        throw ExceptionFactory.StudyGroupNotFound();
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
                throw ExceptionFactory.StudyGroupNotYours();
            }
        } else {
            throw ExceptionFactory.StudyGroupNotFound();
        }
    }
}
