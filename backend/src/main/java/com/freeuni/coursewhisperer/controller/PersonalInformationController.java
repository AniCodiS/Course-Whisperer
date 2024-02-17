package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.api.dto.UpdatePersonalInformationDTO;
import com.freeuni.coursewhisperer.service.PersonalInformationService;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/personal-information")
public class PersonalInformationController {

    private final PersonalInformationService personalInformationService;

    public PersonalInformationController(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    @GetMapping
    public List<PersonalInformationDTO> getAllPersonalInformation() {
        return personalInformationService.getAllPersonalInformation();
    }

    @GetMapping("/{email}")
    public PersonalInformationDTO getPersonalInformationByUsername(@PathVariable String email) {
        return personalInformationService.getPersonalInformationByEmail(email);
    }

    @PostMapping
    public PersonalInformationDTO createPersonalInformation(@RequestBody PersonalInformationDTO personalInformationDTO) {
        return personalInformationService.createPersonalInformation(personalInformationDTO);
    }

    @PutMapping("/{email}")
    public PersonalInformationDTO updatePersonalInformation(@PathVariable String email, @RequestBody UpdatePersonalInformationDTO updatePersonalInformationDTO) {
        return personalInformationService.updatePersonalInformation(email, updatePersonalInformationDTO);
    }

    @DeleteMapping("/{email}")
    public void deletePersonalInformation(@PathVariable String email) {
        personalInformationService.deletePersonalInformation(email);
    }
}
