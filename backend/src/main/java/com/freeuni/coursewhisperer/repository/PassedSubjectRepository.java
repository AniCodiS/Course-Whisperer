package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.PassedSubjectEntity;
import com.freeuni.coursewhisperer.data.enums.EGrade;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PassedSubjectRepository extends AbstractRepository<PassedSubjectEntity, Long> {
    @Query("select s from PassedSubjectEntity s where s.username = :username and s.grade != :grade")
    List<String> findByUsernameAndGrade(String username, EGrade grade);

    Optional<PassedSubjectEntity> findById(Long id);

    List<PassedSubjectEntity> findByUsername(String username);
}
