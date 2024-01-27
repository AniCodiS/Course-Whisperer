package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.entity.StudyGroupMember;
import com.freeuni.coursewhisperer.data.mapper.StudyGroupMemberMapper;
import com.freeuni.coursewhisperer.repository.StudyGroupMemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudyGroupMemberService {

    private final StudyGroupMemberRepository studyGroupMemberRepository;

    private final StudyGroupMemberMapper mapper;

    public StudyGroupMemberService(StudyGroupMemberRepository studyGroupMemberRepository, StudyGroupMemberMapper mapper) {
        this.studyGroupMemberRepository = studyGroupMemberRepository;
        this.mapper = mapper;
    }

    public List<StudyGroupMemberDTO> getAllStudyGroupMembers() {
        List<StudyGroupMember> studyGroupMembers = studyGroupMemberRepository.findAll();
        List<StudyGroupMemberDTO> studyGroupMemberDTOs = new ArrayList<>();
        for (StudyGroupMember studyGroupMember : studyGroupMembers) {
            studyGroupMemberDTOs.add(mapper.modelToDto(studyGroupMember));
        }
        return studyGroupMemberDTOs;
    }

    public StudyGroupMember getStudyGroupMemberById(Long id) {
        return studyGroupMemberRepository.findById(id).orElse(null);
    }

    public StudyGroupMemberDTO createStudyGroupMember(StudyGroupMemberDTO studyGroupMemberDTO) {
        return mapper.modelToDto(studyGroupMemberRepository.save(mapper.dtoToModel(studyGroupMemberDTO)));
    }

    public StudyGroupMember updateStudyGroupMember(Long id, StudyGroupMember studyGroupMember) {
        if (studyGroupMemberRepository.existsById(id)) {
            studyGroupMember.setId(id);
            return studyGroupMemberRepository.save(studyGroupMember);
        }
        return null;
    }

    public void deleteStudyGroupMember(Long id) {
        studyGroupMemberRepository.deleteById(id);
    }
}
