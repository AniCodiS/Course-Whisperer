package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.mapper.LecturerMapper;
import com.freeuni.coursewhisperer.data.api.dto.CreatedLecturerDTO;
import com.freeuni.coursewhisperer.data.api.dto.LecturerDTO;
import com.freeuni.coursewhisperer.data.entity.Lecturer;
import com.freeuni.coursewhisperer.repository.LecturerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;
    private final LecturerMapper mapper;

    public LecturerService(LecturerRepository lecturerRepository, LecturerMapper mapper) {
        this.lecturerRepository = lecturerRepository;
        this.mapper = mapper;
    }

    public List<LecturerDTO> getAllLecturers() {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        List<LecturerDTO> lecturerDTOS = new ArrayList<>();
        for (Lecturer lecturer : lecturers) {
            lecturerDTOS.add(mapper.modelToDto(lecturer));
        }
        return lecturerDTOS;
    }

    public LecturerDTO getLecturerById(Long id) {
        return mapper.modelToDto(lecturerRepository.findById(id).orElse(null));
    }

    public CreatedLecturerDTO createLecturer(LecturerDTO lecturer) {
        CreatedLecturerDTO createdLecturerDTO = new CreatedLecturerDTO();
        Lecturer createdLecturer = lecturerRepository.save(mapper.dtoToModel(lecturer));
        createdLecturerDTO.setId(createdLecturer.getId());
        createdLecturerDTO.setLecturerName(createdLecturer.getLecturerName());
        createdLecturerDTO.setDepartment(createdLecturer.getDepartment());
        createdLecturerDTO.setEmail(createdLecturer.getEmail());
        return createdLecturerDTO;
    }

    public LecturerDTO updateLecturer(String email, LecturerDTO lecturer) {
        if (lecturerRepository.existsByEmail(email)) {
            Lecturer lecturerEntity = mapper.dtoToModel(lecturer);
            lecturerEntity.setId(lecturerRepository.findByEmail(email).getId());
            return mapper.modelToDto(lecturerRepository.save(lecturerEntity));
        }
        return null;
    }

    public void deleteLecturer(Long id) {
        lecturerRepository.deleteById(id);
    }
}
