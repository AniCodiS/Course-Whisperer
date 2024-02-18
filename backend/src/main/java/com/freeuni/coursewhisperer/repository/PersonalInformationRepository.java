package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.PersonalInformationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInformationRepository extends AbstractRepository<PersonalInformationEntity, Long> {
    boolean existsByEmail(String email);

    PersonalInformationEntity findByEmail(String email);

    void deleteByEmail(String email);
}
