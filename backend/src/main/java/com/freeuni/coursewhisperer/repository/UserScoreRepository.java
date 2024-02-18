package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.UserScoreEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScoreRepository extends AbstractRepository<UserScoreEntity, Long> {
    boolean existsByUsername(String username);

    UserScoreEntity findByUsername(String username);

    void deleteByUsername(String username);
}
