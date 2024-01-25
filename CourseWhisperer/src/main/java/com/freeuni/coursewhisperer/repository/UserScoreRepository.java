package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.model.db.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
}
