package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationResponse;
import com.freeuni.coursewhisperer.data.api.dto.UpdatePersonalInformationDTO;
import com.freeuni.coursewhisperer.exception.CourseWhispererException;
import com.freeuni.coursewhisperer.service.PersonalInformationService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all")
    public ResponseEntity<List<PersonalInformationResponse>> getAllPersonalInformation() {
        try {
            return ResponseEntity.ok().body(personalInformationService.getAllPersonalInformation());
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(List.of(new PersonalInformationResponse(e.getErrorDescription())));
        }
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<PersonalInformationResponse> getPersonalInformationByUsername(@PathVariable String email) {
        try {
            return ResponseEntity.ok().body(personalInformationService.getPersonalInformationByEmail(email));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new PersonalInformationResponse(e.getErrorDescription()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PersonalInformationResponse> createPersonalInformation(@RequestBody PersonalInformationDTO personalInformationDTO) {
        try {
            return ResponseEntity.ok().body(personalInformationService.createPersonalInformation(personalInformationDTO));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new PersonalInformationResponse(e.getErrorDescription()));
        }
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<PersonalInformationResponse> updatePersonalInformation(@PathVariable String email, @RequestBody UpdatePersonalInformationDTO updatePersonalInformationDTO) {
        try {
            return ResponseEntity.ok().body(personalInformationService.updatePersonalInformation(email, updatePersonalInformationDTO));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new PersonalInformationResponse(e.getErrorDescription()));
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deletePersonalInformation(@PathVariable String email) {
        try {
            personalInformationService.deletePersonalInformation(email);
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getErrorDescription());
        }
        return ResponseEntity.ok().body("Personal information deleted successfully");
    }
}
