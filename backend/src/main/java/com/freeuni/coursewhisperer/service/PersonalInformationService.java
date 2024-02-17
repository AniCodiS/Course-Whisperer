package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.entity.PersonalInformation;
import com.freeuni.coursewhisperer.data.entity.User;
import com.freeuni.coursewhisperer.data.mapper.PersonalInformationMapper;
import com.freeuni.coursewhisperer.repository.PersonalInformationRepository;
import com.freeuni.coursewhisperer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalInformationService {

    private final PersonalInformationRepository personalInformationRepository;

    private final UserRepository userRepository;

    private final PersonalInformationMapper mapper;

    public PersonalInformationService(PersonalInformationRepository personalInformationRepository, UserRepository userRepository, PersonalInformationMapper mapper) {
        this.personalInformationRepository = personalInformationRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<PersonalInformationDTO> getAllPersonalInformation() {
        List<PersonalInformation> personalInfos = personalInformationRepository.findAll();
        List<PersonalInformationDTO> personalInformationDTOs = new ArrayList<>();
        for (PersonalInformation personalInformation : personalInfos) {
            personalInformationDTOs.add(mapper.modelToDto(personalInformation));
        }
        return personalInformationDTOs;
    }

    public PersonalInformationDTO getPersonalInformationByEmail(String email) {
        return mapper.modelToDto(personalInformationRepository.findByEmail(email));
    }

    public PersonalInformationDTO createPersonalInformation(PersonalInformationDTO personalInformationDTO) {
        PersonalInformationDTO createdPersonalInformationDTO = new PersonalInformationDTO();
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setUser(userRepository.findByEmail(personalInformationDTO.getEmail()));
        personalInformation.setFirstName(personalInformationDTO.getFirstName());
        personalInformation.setLastName(personalInformationDTO.getLastName());
        personalInformation.setYear(personalInformationDTO.getYear());
        personalInformation.setFaculty(personalInformationDTO.getFaculty());
        personalInformation.setEmail(personalInformationDTO.getEmail());
        personalInformationRepository.save(personalInformation);
        createdPersonalInformationDTO.setFirstName(personalInformation.getFirstName());
        createdPersonalInformationDTO.setLastName(personalInformation.getLastName());
        createdPersonalInformationDTO.setYear(personalInformation.getYear());
        createdPersonalInformationDTO.setFaculty(personalInformation.getFaculty());
        createdPersonalInformationDTO.setEmail(personalInformation.getEmail());
        return createdPersonalInformationDTO;
    }

    public PersonalInformationDTO updatePersonalInformation(String email, PersonalInformationDTO personalInformationDTO) {
        if (personalInformationRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email);
            PersonalInformation personalInformation = mapper.dtoToModel(personalInformationDTO);
            personalInformation.setUser(user);
            personalInformation.setId(personalInformationRepository.findByEmail(email).getId());
            return mapper.modelToDto(personalInformationRepository.save(personalInformation));
        }
        return null;
    }

    @Transactional
    public void deletePersonalInformation(String email) {
        personalInformationRepository.deleteByEmail(email);
    }
}
