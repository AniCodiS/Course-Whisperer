package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.CreatedLecturerDTO;
import com.freeuni.coursewhisperer.data.api.dto.LecturerDTO;
import com.freeuni.coursewhisperer.data.api.dto.LecturerDTOResponse;
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
    public ResponseEntity<List<LecturerDTOResponse>> getAllLecturers() {
        try {
            return ResponseEntity.ok().body(lecturerService.getAllLecturers());
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(List.of(new LecturerDTOResponse(e.getErrorDescription())));
        }
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<LecturerDTOResponse> getLecturerByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok().body(lecturerService.getLecturerByEmail(email));
        } catch(CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new LecturerDTOResponse(e.getErrorDescription()));
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
    public ResponseEntity<LecturerDTOResponse> updateLecturer(@PathVariable String email, @RequestBody LecturerDTO lecturer) {
        try {
            return ResponseEntity.ok().body(lecturerService.updateLecturer(email, lecturer));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new LecturerDTOResponse(e.getErrorDescription()));
        }
    }

    @DeleteMapping("/delete/{email}")
    public void deleteLecturer(@PathVariable String email) {
        try {
            lecturerService.deleteLecturer(email);
        } catch (CourseWhispererException e) {
            throw new CourseWhispererException(e.getErrorKey(), e.getErrorDescription(), e.getStatus());
        }
    }
}
