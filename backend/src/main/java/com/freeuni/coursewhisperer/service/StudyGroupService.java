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
            StudyGroupResponse studyGroupResponse = studyGroupResponseMapper.modelToDto(studyGroupResponseMapper.entityToModel(studyGroup));
            studyGroupResponse.setCurrentMemberCount(studyGroup.getCurrentMemberCount());
            studyGroupResponse.setMaxMemberCount(studyGroup.getMaxMemberCount());
            studyGroupResponses.add(studyGroupResponse);
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
        studyGroupDTO.setCreatorUsername(studyGroupDTO.getCreatorUsername());
        StudyGroupEntity studyGroupEntity = mapper.modelToEntity(mapper.dtoToModel(studyGroupDTO));
        studyGroupEntity.setCreatorUsername(studyGroupDTO.getCreatorUsername());
        studyGroupEntity.setCurrentMemberCount(0);
        studyGroupEntity.setMaxMemberCount(10);
        StudyGroupEntity savedStudyGroupEntity = studyGroupRepository.save(studyGroupEntity);
        StudyGroupResponse studyGroupResponse = studyGroupResponseMapper.modelToDto(studyGroupResponseMapper.entityToModel(savedStudyGroupEntity));
        studyGroupResponse.setCurrentMemberCount(0);
        studyGroupResponse.setMaxMemberCount(10);
        return studyGroupResponse;
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

    public List<StudyGroupResponse> filterStudyGroupsBySubject(String subjectName) {
        List<StudyGroupEntity> studyGroups = studyGroupRepository.findBySubjectName(subjectName);
        if (studyGroups.isEmpty()) {
            throw ExceptionFactory.NoStudyGroupsPresent();
        }
        List<StudyGroupResponse> studyGroupResponses = new ArrayList<>();
        for (StudyGroupEntity studyGroup : studyGroups) {
            StudyGroupResponse studyGroupResponse = studyGroupResponseMapper.modelToDto(studyGroupResponseMapper.entityToModel(studyGroup));
            studyGroupResponse.setCurrentMemberCount(studyGroup.getCurrentMemberCount());
            studyGroupResponse.setMaxMemberCount(studyGroup.getMaxMemberCount());
            studyGroupResponse.setGroupName(studyGroup.getGroupName());
            studyGroupResponse.setMeetingTime(studyGroup.getMeetingTime());
            studyGroupResponses.add(studyGroupResponse);
        }
        return studyGroupResponses;
    }
}
