package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.entity.PersonalInformationEntity;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
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
        List<PersonalInformationEntity> personalInfos = personalInformationRepository.findAll();
        List<PersonalInformationDTO> personalInformationDTOs = new ArrayList<>();
        for (PersonalInformationEntity personalInformation : personalInfos) {
            personalInformationDTOs.add(mapper.modelToDto(mapper.entityToModel(personalInformation)));
        }
        return personalInformationDTOs;
    }

    public PersonalInformationDTO getPersonalInformationByEmail(String email) {
        return mapper.modelToDto(mapper.entityToModel(personalInformationRepository.findByEmail(email)));
    }

    public PersonalInformationDTO createPersonalInformation(PersonalInformationDTO personalInformationDTO) {
        PersonalInformationDTO createdPersonalInformationDTO = new PersonalInformationDTO();
        PersonalInformationEntity personalInformation = new PersonalInformationEntity();
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
            UserEntity user = userRepository.findByEmail(email);
            PersonalInformationEntity personalInformationEntity = mapper.modelToEntity(mapper.dtoToModel(personalInformationDTO));
            personalInformationEntity.setUser(user);
            personalInformationEntity.setId(personalInformationRepository.findByEmail(email).getId());
            return mapper.modelToDto(mapper.entityToModel(personalInformationRepository.save(personalInformationEntity)));
        }
        return null;
    }

    @Transactional
    public void deletePersonalInformation(String email) {
        personalInformationRepository.deleteByEmail(email);
    }
}