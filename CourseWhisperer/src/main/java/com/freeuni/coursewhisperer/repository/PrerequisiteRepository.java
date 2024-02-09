package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.PrerequisiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrerequisiteRepository extends JpaRepository<PrerequisiteEntity, Long> {
    boolean existsPrerequisiteById(Long id);

    PrerequisiteEntity findPrerequisiteById(Long id);

    void deletePrerequisiteById(Long id);
}
