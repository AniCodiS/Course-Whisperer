package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.model.db.Lecturer;
import com.freeuni.coursewhisperer.service.LecturerService;
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
@RequestMapping("/api/lecturers")
public class LecturerController {

    private final LecturerService lecturerService;

    @Autowired
    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public List<Lecturer> getAllLecturers() {
        return lecturerService.getAllLecturers();
    }

    @GetMapping("/{id}")
    public Lecturer getLecturerById(@PathVariable Long id) {
        return lecturerService.getLecturerById(id);
    }

    @PostMapping
    public Lecturer createLecturer(@RequestBody Lecturer lecturer) {
        return lecturerService.createLecturer(lecturer);
    }

    @PutMapping("/{id}")
    public Lecturer updateLecturer(@PathVariable Long id, @RequestBody Lecturer lecturer) {
        return lecturerService.updateLecturer(id, lecturer);
    }

    @DeleteMapping("/{id}")
    public void deleteLecturer(@PathVariable Long id) {
        lecturerService.deleteLecturer(id);
    }
}
