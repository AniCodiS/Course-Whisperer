package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.CreatedLecturerDTO;
import com.freeuni.coursewhisperer.data.api.dto.LecturerDTO;
import com.freeuni.coursewhisperer.service.LecturerService;
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
@RequestMapping("/api/lecturers")
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public List<LecturerDTO> getAllLecturers() {
        return lecturerService.getAllLecturers();
    }

    @GetMapping("/{email}")
    public LecturerDTO getLecturerByEmail(@PathVariable String email) {
        return lecturerService.getLecturerByEmail(email);
    }

    @PostMapping
    public CreatedLecturerDTO createLecturer(@RequestBody LecturerDTO lecturer) {
        return lecturerService.createLecturer(lecturer);
    }

    @PutMapping("/{email}")
    public LecturerDTO updateLecturer(@PathVariable String email, @RequestBody LecturerDTO lecturer) {
        return lecturerService.updateLecturer(email, lecturer);
    }

    @DeleteMapping("/{email}")
    public void deleteLecturer(@PathVariable String email) {
        lecturerService.deleteLecturer(email);
    }
}
