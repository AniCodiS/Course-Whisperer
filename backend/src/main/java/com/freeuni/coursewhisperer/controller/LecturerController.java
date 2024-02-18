package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.CreatedLecturerDTO;
import com.freeuni.coursewhisperer.data.api.dto.LecturerDTO;
import com.freeuni.coursewhisperer.data.api.dto.LecturerResponse;
import com.freeuni.coursewhisperer.exception.CourseWhispererException;
import com.freeuni.coursewhisperer.service.LecturerService;
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
@RequestMapping("/api/lecturer")
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LecturerResponse>> getAllLecturers() {
        try {
            return ResponseEntity.ok().body(lecturerService.getAllLecturers());
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(List.of(new LecturerResponse(e.getErrorDescription())));
        }
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<LecturerResponse> getLecturerByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok().body(lecturerService.getLecturerByEmail(email));
        } catch(CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new LecturerResponse(e.getErrorDescription()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CreatedLecturerDTO> createLecturer(@RequestBody LecturerDTO lecturer) {
        try {
            return ResponseEntity.ok().body(lecturerService.createLecturer(lecturer));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new CreatedLecturerDTO(e.getErrorDescription()));
        }
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<LecturerResponse> updateLecturer(@PathVariable String email, @RequestBody LecturerDTO lecturer) {
        try {
            return ResponseEntity.ok().body(lecturerService.updateLecturer(email, lecturer));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new LecturerResponse(e.getErrorDescription()));
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteLecturer(@PathVariable String email) {
        try {
            lecturerService.deleteLecturer(email);
        } catch (CourseWhispererException e) {
            ResponseEntity.status(e.getStatus()).body(e.getErrorDescription());
        }
        return ResponseEntity.ok().body("Personal information deleted successfully");
    }
}
