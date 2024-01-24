package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.model.Lecturer;
import com.freeuni.coursewhisperer.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Lecturer getLecturerById(Long id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    public Lecturer createLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    public Lecturer updateLecturer(Long id, Lecturer lecturer) {
        if (lecturerRepository.existsById(id)) {
            lecturer.setId(id);
            return lecturerRepository.save(lecturer);
        }
        return null;
    }

    public void deleteLecturer(Long id) {
        lecturerRepository.deleteById(id);
    }
}
