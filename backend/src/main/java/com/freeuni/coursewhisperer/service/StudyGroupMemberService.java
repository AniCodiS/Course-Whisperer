package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.entity.StudyGroupEntity;
import com.freeuni.coursewhisperer.data.entity.StudyGroupMemberEntity;
import com.freeuni.coursewhisperer.data.mapper.StudyGroupMemberMapper;
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

    private final StudyGroupMemberMapper mapper;

    public StudyGroupMemberService(StudyGroupMemberRepository studyGroupMemberRepository, StudyGroupRepository studyGroupRepository, UserRepository userRepository, StudyGroupMemberMapper mapper) {
        this.studyGroupMemberRepository = studyGroupMemberRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<StudyGroupMemberDTO> getAllStudyGroupMembers() {
        List<StudyGroupMemberEntity> studyGroupMembers = studyGroupMemberRepository.findAll();
        List<StudyGroupMemberDTO> studyGroupMemberDTOs = new ArrayList<>();
        for (StudyGroupMemberEntity studyGroupMember : studyGroupMembers) {
            StudyGroupMemberDTO studyGroupMemberDTO = new StudyGroupMemberDTO();
            studyGroupMemberDTO.setGroupName(studyGroupMember.getStudyGroup().getGroupName());
            studyGroupMemberDTO.setMemberUsername(studyGroupMember.getMember().getUsername());
            studyGroupMemberDTOs.add(studyGroupMemberDTO);
        }
        return studyGroupMemberDTOs;
    }

    public StudyGroupMemberEntity getStudyGroupMemberById(Long id) {
        return studyGroupMemberRepository.findById(id).orElse(null);
    }

    public StudyGroupMemberDTO createStudyGroupMember(StudyGroupMemberDTO studyGroupMemberDTO) {
        String studyGroupName = studyGroupMemberDTO.getGroupName();
        String memberUsername = studyGroupMemberDTO.getMemberUsername();
        StudyGroupMemberEntity studyGroupMember = new StudyGroupMemberEntity();
        StudyGroupEntity studyGroup = studyGroupRepository.findByGroupName(studyGroupName);
        if (studyGroup.getCurrentMemberCount() < studyGroup.getMaxMemberCount()) {
            studyGroup.setCurrentMemberCount(studyGroup.getCurrentMemberCount() + 1);
            studyGroupRepository.save(studyGroup);
        } else {
            return null;
        }
        studyGroupMember.setStudyGroup(studyGroupRepository.findByGroupName(studyGroupName));
        studyGroupMember.setMember(userRepository.findByUsername(memberUsername));
        studyGroupMemberRepository.save(studyGroupMember);

        StudyGroupMemberDTO studyGroupMemberDTO2 = new StudyGroupMemberDTO();
        studyGroupMemberDTO2.setGroupName(studyGroupMember.getStudyGroup().getGroupName());
        studyGroupMemberDTO2.setMemberUsername(studyGroupMember.getMember().getUsername());
        return studyGroupMemberDTO2;
    }

    public StudyGroupMemberEntity updateStudyGroupMember(Long id, StudyGroupMemberEntity studyGroupMember) {
        if (studyGroupMemberRepository.existsById(id)) {
            studyGroupMember.setId(id);
            return studyGroupMemberRepository.save(studyGroupMember);
        }
        return null;
    }

    @Transactional
    public void deleteStudyGroupMember(Long id) {
        studyGroupMemberRepository.deleteById(id);
    }
}
