package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.model.db.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {
}
