package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PrerequisiteDTO;
import com.freeuni.coursewhisperer.data.entity.Prerequisite;
import com.freeuni.coursewhisperer.data.mapper.PrerequisiteMapper;
import com.freeuni.coursewhisperer.repository.PrerequisiteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrerequisiteService {

    private final PrerequisiteRepository prerequisiteRepository;

    private final PrerequisiteMapper mapper;

    public PrerequisiteService(PrerequisiteRepository prerequisiteRepository, PrerequisiteMapper mapper) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.mapper = mapper;
    }

    public List<PrerequisiteDTO> getAllPrerequisites() {
        List<Prerequisite> prerequisites = prerequisiteRepository.findAll();
        List<PrerequisiteDTO> prerequisiteDTOs = new ArrayList<>();
        for (Prerequisite prerequisite : prerequisites) {
            prerequisiteDTOs.add(mapper.modelToDto(prerequisite));
        }
        return prerequisiteDTOs;
    }

    public PrerequisiteDTO getPrerequisiteBySubjectName(String subjectName) {
        return mapper.modelToDto(prerequisiteRepository.findBySubjectName(subjectName));
    }

    public PrerequisiteDTO createPrerequisite(PrerequisiteDTO prerequisite) {
        return mapper.modelToDto(prerequisiteRepository.save(mapper.dtoToModel(prerequisite)));
    }

    public PrerequisiteDTO updatePrerequisite(String subjectName, PrerequisiteDTO prerequisiteDTO) {
        if (prerequisiteRepository.existsBySubjectName(subjectName)) {
            Prerequisite prerequisite = mapper.dtoToModel(prerequisiteDTO);
            prerequisite.setId(prerequisiteRepository.findBySubjectName(subjectName).getId());
            return mapper.modelToDto(prerequisiteRepository.save(prerequisite));
        }
        return null;
    }

    public void deletePrerequisite(String subjectName) {
        prerequisiteRepository.deleteBySubjectName(subjectName);
    }
}
