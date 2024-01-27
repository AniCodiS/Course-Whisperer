package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    User findByUsername(String username);

    void deleteByUsername(String username);
}
