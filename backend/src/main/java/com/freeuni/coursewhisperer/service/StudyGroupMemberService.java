package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.DeleteStudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberResponse;
import com.freeuni.coursewhisperer.data.entity.StudyGroupEntity;
import com.freeuni.coursewhisperer.data.entity.StudyGroupMemberEntity;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.StudyGroupMemberRepository;
import com.freeuni.coursewhisperer.repository.StudyGroupRepository;
import com.freeuni.coursewhisperer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudyGroupMemberService {

    private final StudyGroupMemberRepository studyGroupMemberRepository;

    private final StudyGroupRepository studyGroupRepository;

    private final UserRepository userRepository;

    public StudyGroupMemberService(StudyGroupMemberRepository studyGroupMemberRepository, StudyGroupRepository studyGroupRepository, UserRepository userRepository) {
        this.studyGroupMemberRepository = studyGroupMemberRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.userRepository = userRepository;
    }

    public List<StudyGroupMemberResponse> getAllStudyGroupMembers() {
        List<StudyGroupMemberEntity> studyGroupMembers = studyGroupMemberRepository.findAll();
        if (studyGroupMembers.isEmpty()) {
            throw ExceptionFactory.NoStudyGroupMembersPresent();
        }
        List<StudyGroupMemberResponse> studyGroupMemberResponses = new ArrayList<>();
        for (StudyGroupMemberEntity studyGroupMember : studyGroupMembers) {
            StudyGroupMemberResponse studyGroupMemberResponse = new StudyGroupMemberResponse();
            studyGroupMemberResponse.setGroupName(studyGroupMember.getStudyGroup().getGroupName());
            studyGroupMemberResponse.setMemberUsername(studyGroupMember.getMember().getUsername());
            studyGroupMemberResponses.add(studyGroupMemberResponse);
        }
        return studyGroupMemberResponses;
    }

    public StudyGroupMemberResponse createStudyGroupMember(StudyGroupMemberDTO studyGroupMemberDTO) {
        String studyGroupName = studyGroupMemberDTO.getGroupName();
        String memberUsername = studyGroupMemberDTO.getMemberUsername();
        StudyGroupMemberEntity member = studyGroupMemberRepository.findByStudyGroupGroupNameAndMemberUsername(studyGroupName, memberUsername);
        if (member != null) {
            throw ExceptionFactory.StudyGroupMemberAlreadyExists();
        }
        if (studyGroupRepository.findByGroupName(studyGroupName) == null) {
            throw ExceptionFactory.StudyGroupWithNameNotFound();
        }
        if (userRepository.findByUsername(memberUsername) == null) {
            throw ExceptionFactory.UserNotFound();
        }
        StudyGroupMemberEntity studyGroupMember = new StudyGroupMemberEntity();
        StudyGroupEntity studyGroup = studyGroupRepository.findByGroupName(studyGroupName);
        if (studyGroup.getCurrentMemberCount() < studyGroup.getMaxMemberCount()) {
            studyGroup.setCurrentMemberCount(studyGroup.getCurrentMemberCount() + 1);
            studyGroupRepository.save(studyGroup);
        } else {
            throw ExceptionFactory.StudyGroupIsFull();
        }
        studyGroupMember.setStudyGroup(studyGroupRepository.findByGroupName(studyGroupName));
        studyGroupMember.setMember(userRepository.findByUsername(memberUsername));
        studyGroupMemberRepository.save(studyGroupMember);
        StudyGroupMemberResponse studyGroupMemberDTO2 = new StudyGroupMemberResponse();
        studyGroupMemberDTO2.setGroupName(studyGroupMember.getStudyGroup().getGroupName());
        studyGroupMemberDTO2.setMemberUsername(studyGroupMember.getMember().getUsername());
        return studyGroupMemberDTO2;
    }

    @Transactional
    public void deleteStudyGroupMember(DeleteStudyGroupMemberDTO deleteStudyGroupMemberDTO) {
        String groupName = deleteStudyGroupMemberDTO.getGroupName();
        String memberUsername = deleteStudyGroupMemberDTO.getMemberUsername();
        StudyGroupMemberEntity member = studyGroupMemberRepository.findByStudyGroupGroupNameAndMemberUsername(groupName, memberUsername);
        if (member != null) {
            studyGroupMemberRepository.delete(member);
            StudyGroupEntity studyGroup = studyGroupRepository.findByGroupName(groupName);
            studyGroup.setCurrentMemberCount(studyGroup.getCurrentMemberCount() - 1);
            studyGroupRepository.save(studyGroup);
        } else {
            throw ExceptionFactory.StudyGroupMemberNotFound();
        }
    }
}
