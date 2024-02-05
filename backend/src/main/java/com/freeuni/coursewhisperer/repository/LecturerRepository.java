package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    boolean existsByEmail(String email);

    Lecturer findByEmail(String email);

    void deleteByEmail(String email);
}
