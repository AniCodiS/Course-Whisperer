package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.model.PersonalInformation;
import com.freeuni.coursewhisperer.repository.PersonalInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonalInformationService {

    private final PersonalInformationRepository personalInformationRepository;

    @Autowired
    public PersonalInformationService(PersonalInformationRepository personalInformationRepository) {
        this.personalInformationRepository = personalInformationRepository;
    }

    public List<PersonalInformation> getAllPersonalInformation() {
        return personalInformationRepository.findAll();
    }

    public PersonalInformation getPersonalInformationById(Long id) {
        return personalInformationRepository.findById(id).orElse(null);
    }

    public PersonalInformation createPersonalInformation(PersonalInformation personalInformation) {
        return personalInformationRepository.save(personalInformation);
    }

    public PersonalInformation updatePersonalInformation(Long id, PersonalInformation personalInformation) {
        if (personalInformationRepository.existsById(id)) {
            personalInformation.setId(id);
            return personalInformationRepository.save(personalInformation);
        }
        return null;
    }

    public void deletePersonalInformation(Long id) {
        personalInformationRepository.deleteById(id);
    }
}
