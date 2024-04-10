package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.LecturerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturerRepository extends AbstractRepository<LecturerEntity, Long> {
    boolean existsByEmail(String email);

    LecturerEntity findByEmail(String email);

    void deleteByEmail(String email);

    @Query("select l from LecturerEntity l where " +
            "(:emails is null or l.email in :emails)")
    List<LecturerEntity> searchByEmails(List<String> emails);
}