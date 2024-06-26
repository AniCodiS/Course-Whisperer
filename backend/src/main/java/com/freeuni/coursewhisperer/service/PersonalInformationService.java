package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationResponse;
import com.freeuni.coursewhisperer.data.api.dto.UpdatePersonalInformationDTO;
import com.freeuni.coursewhisperer.data.entity.PersonalInformationEntity;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import com.freeuni.coursewhisperer.data.mapper.PersonalInformationMapper;
import com.freeuni.coursewhisperer.data.mapper.PersonalInformationResponseMapper;
import com.freeuni.coursewhisperer.data.mapper.UpdatePersonalInformationMapper;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
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

    private final UpdatePersonalInformationMapper updateMapper;

    private final PersonalInformationResponseMapper personalInformationResponseMapper;

    public PersonalInformationService(PersonalInformationRepository personalInformationRepository, UserRepository userRepository, UpdatePersonalInformationMapper updateMapper, PersonalInformationResponseMapper personalInformationResponseMapper) {
        this.personalInformationRepository = personalInformationRepository;
        this.userRepository = userRepository;
        this.updateMapper = updateMapper;
        this.personalInformationResponseMapper = personalInformationResponseMapper;
    }

    public List<PersonalInformationResponse> getAllPersonalInformation() {
        List<PersonalInformationEntity> personalInfos = personalInformationRepository.findAll();
        if (personalInfos.isEmpty()) {
            throw ExceptionFactory.NoPersonalInformationPresent();
        }
        List<PersonalInformationResponse> personalInformationDTOs = new ArrayList<>();
        for (PersonalInformationEntity personalInformation : personalInfos) {
            personalInformationDTOs.add(personalInformationResponseMapper.modelToDto(personalInformationResponseMapper.entityToModel(personalInformation)));
        }
        return personalInformationDTOs;
    }

    public PersonalInformationResponse getPersonalInformation(String username) {
        String email;
        if (userRepository.existsByUsername(username)) {
            email = userRepository.findByUsername(username).getEmail();
        } else {
            throw ExceptionFactory.UserNotFound();
        }
        if (personalInformationRepository.existsByEmail(email)) {
            return personalInformationResponseMapper.modelToDto(personalInformationResponseMapper.entityToModel(personalInformationRepository.findByEmail(email)));
        }
        throw ExceptionFactory.PersonalInformationNotFound();
    }

    public PersonalInformationResponse createPersonalInformation(PersonalInformationDTO personalInformationDTO) {
        if (!userRepository.existsByEmail(personalInformationDTO.getEmail())) {
            throw ExceptionFactory.UserNotFound();
        }
        if (personalInformationRepository.existsByEmail(personalInformationDTO.getEmail())) {
            throw ExceptionFactory.PersonalInformationAlreadyExists();
        }
        PersonalInformationResponse createdPersonalInformationDTO = new PersonalInformationResponse();
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

    public PersonalInformationResponse updatePersonalInformation(String username, UpdatePersonalInformationDTO updatePersonalInformationDTO) {
        String email;
        if (userRepository.existsByUsername(username)) {
            email = userRepository.findByUsername(username).getEmail();
        } else {
            throw ExceptionFactory.UserNotFound();
        }
        if (personalInformationRepository.existsByEmail(email)) {
            UserEntity user = userRepository.findByEmail(email);
            PersonalInformationEntity personalInformationEntity = updateMapper.modelToEntity(updateMapper.dtoToModel(updatePersonalInformationDTO));
            personalInformationEntity.setEmail(email);
            personalInformationEntity.setUser(user);
            personalInformationEntity.setId(personalInformationRepository.findByEmail(email).getId());
            if (updatePersonalInformationDTO.getFirstName() != null) {
                personalInformationEntity.setFirstName(updatePersonalInformationDTO.getFirstName());
            } else {
                personalInformationEntity.setFirstName(personalInformationRepository.findByEmail(email).getFirstName());
            }
            if (updatePersonalInformationDTO.getLastName() != null) {
                personalInformationEntity.setLastName(updatePersonalInformationDTO.getLastName());
            } else {
                personalInformationEntity.setLastName(personalInformationRepository.findByEmail(email).getLastName());
            }
            if (updatePersonalInformationDTO.getYear() != null) {
                personalInformationEntity.setYear(updatePersonalInformationDTO.getYear());
            } else {
                personalInformationEntity.setYear(personalInformationRepository.findByEmail(email).getYear());
            }
            if (updatePersonalInformationDTO.getFaculty() != null) {
                personalInformationEntity.setFaculty(updatePersonalInformationDTO.getFaculty());
            } else {
                personalInformationEntity.setFaculty(personalInformationRepository.findByEmail(email).getFaculty());
            }
            return personalInformationResponseMapper.modelToDto(personalInformationResponseMapper.entityToModel(personalInformationRepository.save(personalInformationEntity)));
        } else {
            throw ExceptionFactory.PersonalInformationNotFound();
        }
    }

    @Transactional
    public void deletePersonalInformation(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        } else {
            throw ExceptionFactory.UserNotFound();
        }
    }
}
