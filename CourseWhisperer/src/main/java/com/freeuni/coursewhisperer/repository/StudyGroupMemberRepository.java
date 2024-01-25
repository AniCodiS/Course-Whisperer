package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.model.db.StudyGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupMemberRepository extends JpaRepository<StudyGroupMember, Long> {
}
