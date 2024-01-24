package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
