package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.mapper.PrerequisiteMapper;
import com.freeuni.coursewhisperer.data.model.Prerequisite;
import com.freeuni.coursewhisperer.repository.PrerequisiteRepository;
import com.freeuni.coursewhisperer.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrerequisiteService {

    private final PrerequisiteRepository prerequisiteRepository;
    private final SubjectRepository subjectRepository;
    private final PrerequisiteMapper prerequisiteMapper;

    public PrerequisiteService(PrerequisiteRepository prerequisiteRepository,
                               SubjectRepository subjectRepository,
                               PrerequisiteMapper prerequisiteMapper) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.subjectRepository = subjectRepository;
        this.prerequisiteMapper = prerequisiteMapper;
    }

    public List<Prerequisite> getPrerequisiteBySubject(String subject) {
        return prerequisiteRepository.findBySubject(subject).stream().map(prerequisiteMapper::entityToModel).toList();
    }

    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteRepository.findAll().stream().map(prerequisiteMapper::entityToModel).toList();
    }
}
