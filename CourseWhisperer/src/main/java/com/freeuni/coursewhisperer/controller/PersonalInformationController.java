package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.model.db.PersonalInformation;
import com.freeuni.coursewhisperer.service.PersonalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PersonalInformationController(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    @GetMapping
    public List<PersonalInformation> getAllPersonalInformation() {
        return personalInformationService.getAllPersonalInformation();
    }

    @GetMapping("/{id}")
    public PersonalInformation getPersonalInformationById(@PathVariable Long id) {
        return personalInformationService.getPersonalInformationById(id);
    }

    @PostMapping
    public PersonalInformation createPersonalInformation(@RequestBody PersonalInformation personalInformation) {
        return personalInformationService.createPersonalInformation(personalInformation);
    }

    @PutMapping("/{id}")
    public PersonalInformation updatePersonalInformation(@PathVariable Long id, @RequestBody PersonalInformation personalInformation) {
        return personalInformationService.updatePersonalInformation(id, personalInformation);
    }

    @DeleteMapping("/{id}")
    public void deletePersonalInformation(@PathVariable Long id) {
        personalInformationService.deletePersonalInformation(id);
    }
}
