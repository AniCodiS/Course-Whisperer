package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.model.Prerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {
}
