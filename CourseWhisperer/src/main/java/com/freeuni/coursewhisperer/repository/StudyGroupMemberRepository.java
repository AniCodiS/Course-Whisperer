package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.StudyGroupMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupMemberRepository extends JpaRepository<StudyGroupMemberEntity, Long> {
}