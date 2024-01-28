package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findBySubjectName(String name);
}
