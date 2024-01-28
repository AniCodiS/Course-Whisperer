package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PrerequisiteDTO;
import com.freeuni.coursewhisperer.data.entity.Prerequisite;
import com.freeuni.coursewhisperer.data.entity.Subject;
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

    public List<PrerequisiteDTO> getAllPrerequisites() {
        List<Prerequisite> prerequisites = prerequisiteRepository.findAll();
        List<PrerequisiteDTO> prerequisiteDTOs = new ArrayList<>();
        for (Prerequisite prerequisite : prerequisites) {
            PrerequisiteDTO prerequisiteDTO = new PrerequisiteDTO();
            Subject subject = subjectRepository.findBySubjectName(prerequisite.getSubject().getSubjectName());
            prerequisiteDTO.setSubjectName(subject.getSubjectName());
            Subject prerequisiteSubject = subjectRepository.findBySubjectName(prerequisite.getPrerequisiteSubject().getSubjectName());
            prerequisiteDTO.setPrerequisiteSubjectName(prerequisiteSubject.getSubjectName());
            prerequisiteDTOs.add(prerequisiteDTO);
        }
        return prerequisiteDTOs;
    }

    public PrerequisiteDTO getPrerequisiteById(Long id) {
        PrerequisiteDTO prerequisiteDTO = new PrerequisiteDTO();
        Prerequisite prerequisite = prerequisiteRepository.findPrerequisiteById(id);
        Subject subject = subjectRepository.findBySubjectName(prerequisite.getSubject().getSubjectName());
        prerequisiteDTO.setSubjectName(subject.getSubjectName());
        Subject prerequisiteSubject = subjectRepository.findBySubjectName(prerequisite.getPrerequisiteSubject().getSubjectName());
        prerequisiteDTO.setPrerequisiteSubjectName(prerequisiteSubject.getSubjectName());
        return prerequisiteDTO;
    }

    public PrerequisiteDTO createPrerequisite(PrerequisiteDTO prerequisite) {
        Subject subject = subjectRepository.findBySubjectName(prerequisite.getSubjectName());
        Subject prerequisiteSubject = subjectRepository.findBySubjectName(prerequisite.getPrerequisiteSubjectName());
        Prerequisite newPrerequisite = new Prerequisite();
        newPrerequisite.setSubject(subject);
        newPrerequisite.setPrerequisiteSubject(prerequisiteSubject);
        prerequisiteRepository.save(newPrerequisite);
        return prerequisite;
    }

    public PrerequisiteDTO updatePrerequisite(Long id, PrerequisiteDTO prerequisiteDTO) {
        if (prerequisiteRepository.existsPrerequisiteById(id)) {
            Subject subject = subjectRepository.findBySubjectName(prerequisiteDTO.getSubjectName());
            Subject prerequisiteSubject = subjectRepository.findBySubjectName(prerequisiteDTO.getPrerequisiteSubjectName());
            Prerequisite prerequisite = new Prerequisite();
            prerequisite.setSubject(subject);
            prerequisite.setPrerequisiteSubject(prerequisiteSubject);
            prerequisite.setId(id);
            prerequisiteRepository.save(prerequisite);
            return prerequisiteDTO;
        }
        return null;
    }

    @Transactional
    public void deletePrerequisite(Long id) {
        prerequisiteRepository.deletePrerequisiteById(id);
    }
}
