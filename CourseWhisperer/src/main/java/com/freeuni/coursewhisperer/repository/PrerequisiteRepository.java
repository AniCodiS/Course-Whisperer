package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.Prerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {
    boolean existsPrerequisiteById(Long id);

    Prerequisite findPrerequisiteById(Long id);

    void deletePrerequisiteById(Long id);
}
