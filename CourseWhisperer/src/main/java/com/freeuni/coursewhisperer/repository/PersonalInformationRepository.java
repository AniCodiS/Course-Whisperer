package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.model.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {
}
