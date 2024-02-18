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
        if (lecturers.isEmpty()) {
            // TODO: throw exception
            return null;
        }
        List<LecturerDTO> lecturerDTOS = new ArrayList<>();
        for (LecturerEntity lecturer : lecturers) {
            lecturerDTOS.add(mapper.modelToDto(mapper.entityToModel(lecturer)));
        }
        return lecturerDTOS;
    }

    public LecturerDTO getLecturerByEmail(String email) {
        if (!lecturerRepository.existsByEmail(email)) {
            return mapper.modelToDto(mapper.entityToModel(lecturerRepository.findByEmail(email)));
        }
        // TODO: throw exception
        return null;
    }

    public CreatedLecturerDTO createLecturer(LecturerDTO lecturer) {
        if (!lecturerRepository.existsByEmail(lecturer.getEmail())) {
            CreatedLecturerDTO createdLecturerDTO = new CreatedLecturerDTO();
            LecturerEntity createdLecturer = lecturerRepository.save(mapper.modelToEntity(mapper.dtoToModel(lecturer)));
            createdLecturerDTO.setId(createdLecturer.getId());
            createdLecturerDTO.setLecturerName(createdLecturer.getLecturerName());
            createdLecturerDTO.setDepartment(createdLecturer.getDepartment());
            createdLecturerDTO.setEmail(createdLecturer.getEmail());
            return createdLecturerDTO;
        }
        // TODO: throw exception
        return null;
    }

    public LecturerDTO updateLecturer(String email, LecturerDTO lecturer) {
        if (lecturerRepository.existsByEmail(email)) {
            LecturerEntity lecturerEntity = mapper.modelToEntity(mapper.dtoToModel(lecturer));
            lecturerEntity.setId(lecturerRepository.findByEmail(email).getId());
            return mapper.modelToDto(mapper.entityToModel(lecturerRepository.save(lecturerEntity)));
        }
        // TODO: throw exception
        return null;
    }

    @Transactional
    public void deleteLecturer(String email) {
        if (lecturerRepository.existsByEmail(email)) {
            lecturerRepository.deleteByEmail(email);
        }
        // TODO: throw exception
    }
}
