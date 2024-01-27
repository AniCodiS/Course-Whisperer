package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.CreatedPersonalInformationDTO;
import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.entity.PersonalInformation;
import com.freeuni.coursewhisperer.data.mapper.PersonalInformationMapper;
import com.freeuni.coursewhisperer.repository.PersonalInformationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalInformationService {

    private final PersonalInformationRepository personalInformationRepository;

    private final PersonalInformationMapper mapper;

    public PersonalInformationService(PersonalInformationRepository personalInformationRepository, PersonalInformationMapper mapper) {
        this.personalInformationRepository = personalInformationRepository;
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

    public PersonalInformationDTO getPersonalInformationByUsername(String email) {
        return mapper.modelToDto(personalInformationRepository.findByAccountMail(email));
    }

    public CreatedPersonalInformationDTO createPersonalInformation(PersonalInformationDTO personalInformationDTO) {
        CreatedPersonalInformationDTO createdPersonalInformationDTO = new CreatedPersonalInformationDTO();
        PersonalInformation personalInformation = personalInformationRepository.save(mapper.dtoToModel(personalInformationDTO));
        createdPersonalInformationDTO.setId(personalInformation.getId());
        createdPersonalInformationDTO.setFirstName(personalInformation.getFirstName());
        createdPersonalInformationDTO.setLastName(personalInformation.getLastName());
        createdPersonalInformationDTO.setYear(personalInformation.getYear());
        createdPersonalInformationDTO.setFaculty(personalInformation.getFaculty());
        createdPersonalInformationDTO.setAccountMail(personalInformation.getAccountMail());
        return createdPersonalInformationDTO;
    }

    public PersonalInformationDTO updatePersonalInformation(String email, PersonalInformationDTO personalInformationDTO) {
        if (personalInformationRepository.existsByAccountMail(email)) {
            PersonalInformation personalInformation = mapper.dtoToModel(personalInformationDTO);
            personalInformation.setId(personalInformationRepository.findByAccountMail(email).getId());
            return mapper.modelToDto(personalInformationRepository.save(personalInformation));
        }
        return null;
    }

    public void deletePersonalInformation(String email) {
        personalInformationRepository.deleteByAccountMail(email);
    }
}
