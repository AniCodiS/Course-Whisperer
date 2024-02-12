package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.LecturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<LecturerEntity, Long> {
    boolean existsByEmail(String email);

    LecturerEntity findByEmail(String email);

    void deleteByEmail(String email);
}
