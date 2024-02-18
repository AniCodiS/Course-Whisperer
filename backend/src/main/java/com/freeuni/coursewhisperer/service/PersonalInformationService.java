package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.api.dto.UpdatePersonalInformationDTO;
import com.freeuni.coursewhisperer.data.entity.PersonalInformationEntity;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import com.freeuni.coursewhisperer.data.mapper.PersonalInformationMapper;
import com.freeuni.coursewhisperer.data.mapper.UpdatePersonalInformationMapper;
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

    private final UpdatePersonalInformationMapper updateMapper;

    public PersonalInformationService(PersonalInformationRepository personalInformationRepository, UserRepository userRepository, PersonalInformationMapper mapper, UpdatePersonalInformationMapper updateMapper) {
        this.personalInformationRepository = personalInformationRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.updateMapper = updateMapper;
    }

    public List<PersonalInformationDTO> getAllPersonalInformation() {
        List<PersonalInformationEntity> personalInfos = personalInformationRepository.findAll();
        if (personalInfos.isEmpty()) {
            // TODO: throw exception
            return null;
        }
        List<PersonalInformationDTO> personalInformationDTOs = new ArrayList<>();
        for (PersonalInformationEntity personalInformation : personalInfos) {
            personalInformationDTOs.add(mapper.modelToDto(mapper.entityToModel(personalInformation)));
        }
        return personalInformationDTOs;
    }

    public PersonalInformationDTO getPersonalInformationByEmail(String email) {
        if (personalInformationRepository.existsByEmail(email)) {
            return mapper.modelToDto(mapper.entityToModel(personalInformationRepository.findByEmail(email)));
        }
        // TODO: throw exception
        return null;
    }

    public PersonalInformationDTO createPersonalInformation(PersonalInformationDTO personalInformationDTO) {
        if (userRepository.existsByEmail(personalInformationDTO.getEmail()) && !personalInformationRepository.existsByEmail(personalInformationDTO.getEmail())) {
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
        // TODO: throw exception
        return null;
    }

    public PersonalInformationDTO updatePersonalInformation(String email, UpdatePersonalInformationDTO updatePersonalInformationDTO) {
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
            return mapper.modelToDto(mapper.entityToModel(personalInformationRepository.save(personalInformationEntity)));
        }
        // TODO: throw exception
        return null;
    }

    @Transactional
    public void deletePersonalInformation(String email) {
        if (personalInformationRepository.existsByEmail(email)) {
            personalInformationRepository.deleteByEmail(email);
        }
        // TODO: throw exception
    }
}
