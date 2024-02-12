package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.StudyGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupRepository extends JpaRepository<StudyGroupEntity, Long> {
    StudyGroupEntity findByGroupName(String name);

    boolean existsByGroupName(String name);

    void deleteByGroupName(String name);
}
