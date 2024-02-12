package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.entity.LecturerEntity;
import com.freeuni.coursewhisperer.data.mapper.LecturerMapper;
import com.freeuni.coursewhisperer.data.api.dto.CreatedLecturerDTO;
import com.freeuni.coursewhisperer.data.api.dto.LecturerDTO;
import com.freeuni.coursewhisperer.repository.LecturerRepository;
import jakarta.transaction.Transactional;
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
        List<LecturerEntity> lecturers = lecturerRepository.findAll();
        List<LecturerDTO> lecturerDTOS = new ArrayList<>();
        for (LecturerEntity lecturer : lecturers) {
            lecturerDTOS.add(mapper.modelToDto(mapper.entityToModel(lecturer)));
        }
        return lecturerDTOS;
    }

    public LecturerDTO getLecturerByEmail(String email) {
        return mapper.modelToDto(mapper.entityToModel(lecturerRepository.findByEmail(email)));
    }

    public CreatedLecturerDTO createLecturer(LecturerDTO lecturer) {
        CreatedLecturerDTO createdLecturerDTO = new CreatedLecturerDTO();
        LecturerEntity createdLecturer = lecturerRepository.save(mapper.modelToEntity(mapper.dtoToModel(lecturer)));
        createdLecturerDTO.setId(createdLecturer.getId());
        createdLecturerDTO.setLecturerName(createdLecturer.getLecturerName());
        createdLecturerDTO.setDepartment(createdLecturer.getDepartment());
        createdLecturerDTO.setEmail(createdLecturer.getEmail());
        return createdLecturerDTO;
    }

    public LecturerDTO updateLecturer(String email, LecturerDTO lecturer) {
        if (lecturerRepository.existsByEmail(email)) {
            LecturerEntity lecturerEntity = mapper.modelToEntity(mapper.dtoToModel(lecturer));
            lecturerEntity.setId(lecturerRepository.findByEmail(email).getId());
            return mapper.modelToDto(mapper.entityToModel(lecturerRepository.save(lecturerEntity)));
        }
        return null;
    }

    @Transactional
    public void deleteLecturer(String email) {
        lecturerRepository.deleteByEmail(email);
    }
}