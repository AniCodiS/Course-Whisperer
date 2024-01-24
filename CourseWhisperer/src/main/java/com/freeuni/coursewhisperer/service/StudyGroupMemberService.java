package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.model.StudyGroupMember;
import com.freeuni.coursewhisperer.repository.StudyGroupMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudyGroupMemberService {

    private final StudyGroupMemberRepository studyGroupMemberRepository;

    @Autowired
    public StudyGroupMemberService(StudyGroupMemberRepository studyGroupMemberRepository) {
        this.studyGroupMemberRepository = studyGroupMemberRepository;
    }

    public List<StudyGroupMember> getAllStudyGroupMembers() {
        return studyGroupMemberRepository.findAll();
    }

    public StudyGroupMember getStudyGroupMemberById(Long id) {
        return studyGroupMemberRepository.findById(id).orElse(null);
    }

    public StudyGroupMember createStudyGroupMember(StudyGroupMember studyGroupMember) {
        return studyGroupMemberRepository.save(studyGroupMember);
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
