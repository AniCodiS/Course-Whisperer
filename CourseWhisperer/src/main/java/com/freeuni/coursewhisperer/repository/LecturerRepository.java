package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.model.db.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
}
