package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<UserEntity, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findByUsernameAndPassword(String username, String password);

    void deleteByUsername(String username);
}
