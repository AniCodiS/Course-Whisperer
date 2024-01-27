package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {
    boolean existsByAccountMail(String accountMail);

    PersonalInformation findByAccountMail(String accountMail);

    void deleteByAccountMail(String accountMail);
}
