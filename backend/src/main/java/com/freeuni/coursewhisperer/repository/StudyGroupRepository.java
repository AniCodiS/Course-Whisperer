package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.StudyGroupEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyGroupRepository extends AbstractRepository<StudyGroupEntity, Long> {
    StudyGroupEntity findByGroupName(String name);

    List<StudyGroupEntity> findBySubjectName(String name);

    boolean existsByGroupName(String name);

    void deleteByGroupName(String name);
}
