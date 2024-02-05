package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {
    boolean existsByEmail(String email);

    PersonalInformation findByEmail(String email);

    void deleteByEmail(String email);
}
