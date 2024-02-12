package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<UserEntity, Long> {
    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    void deleteByUsername(String username);

    UserEntity findByEmail(String email);
}
