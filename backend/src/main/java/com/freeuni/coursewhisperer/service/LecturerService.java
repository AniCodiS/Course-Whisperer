package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.CreatedLecturerDTO;
import com.freeuni.coursewhisperer.data.api.dto.LecturerDTO;
import com.freeuni.coursewhisperer.data.api.dto.LecturerResponse;
import com.freeuni.coursewhisperer.data.entity.LecturerEntity;
import com.freeuni.coursewhisperer.data.mapper.LecturerMapper;
import com.freeuni.coursewhisperer.data.mapper.LecturerResponseMapper;
import com.freeuni.coursewhisperer.data.model.Lecturer;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.LecturerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;
    private final LecturerMapper mapper;
    private final LecturerResponseMapper lecturerDTOResponseMapper;

    public LecturerService(LecturerRepository lecturerRepository, LecturerMapper mapper, LecturerResponseMapper lecturerResponseMapper) {
        this.lecturerRepository = lecturerRepository;
        this.mapper = mapper;
        this.lecturerDTOResponseMapper = lecturerResponseMapper;
    }

    public List<LecturerResponse> getAllLecturers() {
        List<LecturerEntity> lecturers = lecturerRepository.findAll();
        if (lecturers.isEmpty()) {
            throw ExceptionFactory.NoLecturersPresent();
        }
        List<LecturerResponse> lecturerDTOS = new ArrayList<>();
        for (LecturerEntity lecturer : lecturers) {
            lecturerDTOS.add(lecturerDTOResponseMapper.modelToDto(lecturerDTOResponseMapper.entityToModel(lecturer)));
        }
        return lecturerDTOS;
    }

    public LecturerResponse getLecturerByEmail(String email) {
        if (!lecturerRepository.existsByEmail(email)) {
            return lecturerDTOResponseMapper.modelToDto(lecturerDTOResponseMapper.entityToModel(lecturerRepository.findByEmail(email)));
        }
        throw ExceptionFactory.LecturerNotFound();
    }

    public List<Lecturer> search(List<String> emails) {
        return lecturerRepository.searchByEmails(emails).stream().map(mapper::entityToModel).toList();
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
        throw ExceptionFactory.LecturerAlreadyExists();
    }

    public LecturerResponse updateLecturer(String email, LecturerDTO lecturer) {
        if (lecturerRepository.existsByEmail(email)) {
            LecturerEntity lecturerEntity = mapper.modelToEntity(mapper.dtoToModel(lecturer));
            lecturerEntity.setId(lecturerRepository.findByEmail(email).getId());
            return lecturerDTOResponseMapper.modelToDto(lecturerDTOResponseMapper.entityToModel(lecturerRepository.save(lecturerEntity)));
        }
        throw ExceptionFactory.LecturerNotFound();
    }

    @Transactional
    public void deleteLecturer(String email) {
        if (lecturerRepository.existsByEmail(email)) {
            lecturerRepository.deleteByEmail(email);
        }
        throw ExceptionFactory.LecturerNotFound();
    }
}
