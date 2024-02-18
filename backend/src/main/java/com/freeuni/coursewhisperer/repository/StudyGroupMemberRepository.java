package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.StudyGroupMemberEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupMemberRepository extends AbstractRepository<StudyGroupMemberEntity, Long> {
    StudyGroupMemberEntity findByStudyGroupGroupNameAndMemberUsername(String groupName, String username);
}
