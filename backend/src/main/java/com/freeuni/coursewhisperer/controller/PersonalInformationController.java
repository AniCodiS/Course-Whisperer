package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationResponse;
import com.freeuni.coursewhisperer.data.api.dto.UpdatePersonalInformationDTO;
import com.freeuni.coursewhisperer.exception.CourseWhispererException;
import com.freeuni.coursewhisperer.service.PersonalInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/get/{username}")
    public ResponseEntity<PersonalInformationResponse> getPersonalInformationByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok().body(personalInformationService.getPersonalInformation(username));
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

    @PutMapping("/update/{username}")
    public ResponseEntity<PersonalInformationResponse> updatePersonalInformation(@PathVariable String username, @RequestBody UpdatePersonalInformationDTO updatePersonalInformationDTO) {
        try {
            return ResponseEntity.ok().body(personalInformationService.updatePersonalInformation(username, updatePersonalInformationDTO));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new PersonalInformationResponse(e.getErrorDescription()));
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deletePersonalInformation(@PathVariable String username) {
        try {
            personalInformationService.deletePersonalInformation(username);
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getErrorDescription());
        }
        return ResponseEntity.ok().body("Personal information deleted successfully");
    }
}
