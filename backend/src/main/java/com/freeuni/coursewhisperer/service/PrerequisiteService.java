package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PrerequisiteDTO;
import com.freeuni.coursewhisperer.data.model.Prerequisite;
import com.freeuni.coursewhisperer.data.model.Subject;
import com.freeuni.coursewhisperer.data.mapper.PrerequisiteMapper;
import com.freeuni.coursewhisperer.repository.PrerequisiteRepository;
import com.freeuni.coursewhisperer.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrerequisiteService {

    private final PrerequisiteRepository prerequisiteRepository;

    private final SubjectRepository subjectRepository;

    private final PrerequisiteMapper mapper;

    public PrerequisiteService(PrerequisiteRepository prerequisiteRepository, SubjectRepository subjectRepository, PrerequisiteMapper mapper) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.subjectRepository = subjectRepository;
        this.mapper = mapper;
    }

    public List<Prerequisite> getAllPrerequisites() {
        return null;
    }

    public PrerequisiteDTO getPrerequisiteById(Long id) {
        return null;

    }

    public PrerequisiteDTO createPrerequisite(PrerequisiteDTO prerequisite) {
        return null;

    }

    public PrerequisiteDTO updatePrerequisite(Long id, PrerequisiteDTO prerequisiteDTO) {

        return null;
    }

    @Transactional
    public void deletePrerequisite(Long id) {
    }
}
