package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.PrerequisiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrerequisiteRepository extends JpaRepository<PrerequisiteEntity, Long> {
    List<PrerequisiteEntity> findBySubject(String subject);
}
