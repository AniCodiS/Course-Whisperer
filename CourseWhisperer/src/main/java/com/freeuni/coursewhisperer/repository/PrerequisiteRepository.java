package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.Prerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {
    Prerequisite findBySubjectName(String subjectName);

    boolean existsBySubjectName(String subjectName);

    void deleteBySubjectName(String subjectName);
}
