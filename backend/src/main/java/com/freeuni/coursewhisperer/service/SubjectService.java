package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.enums.ESchool;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import com.freeuni.coursewhisperer.data.mapper.SubjectMapper;
import com.freeuni.coursewhisperer.data.model.Prerequisite;
import com.freeuni.coursewhisperer.data.model.Subject;
import com.freeuni.coursewhisperer.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final PassedSubjectService passedSubjectService;
    private final PrerequisiteService prerequisiteService;
    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository,
                          PassedSubjectService passedSubjectService,
                          PrerequisiteService prerequisiteService,
                          SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.passedSubjectService = passedSubjectService;
        this.prerequisiteService = prerequisiteService;
        this.subjectMapper = subjectMapper;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll().stream().map(subjectMapper::entityToModel).toList();
    }

    public List<Subject> search(String name, String code, ESchool schoolName,
                                Integer creditScore, Long lecturer, ESemester semester) {
        return subjectRepository.search(name, code, schoolName, creditScore, lecturer, semester).
                stream().map(subjectMapper::entityToModel).toList();
    }

    public List<Subject> chooseSubject(String username, ESchool schoolName, Integer creditScore, ESemester semester) {
        var passedSubjects = passedSubjectService.getPassesSubjects(username);
        var prerequisites = prerequisiteService.getAllPrerequisites().stream().collect(Collectors.groupingBy(
                Prerequisite::getSubject,
                Collectors.mapping(Prerequisite::getPrerequisite, Collectors.toList())
        ));

        List<Subject> subjects = new ArrayList<>();

        subjectRepository.search(null, null, schoolName, creditScore, null, semester).
                stream().map(subjectMapper::entityToModel).forEach(subject -> {
                            if (!passedSubjects.contains(subject.getCode()) &&
                                    new HashSet<>(passedSubjects).containsAll(prerequisites.get(subject))) {
                                subjects.add(subject);
                            }
                        }
                );
        return subjects;
    }

    public Subject createSubject(Subject subject) {
        return subjectMapper.entityToModel(subjectRepository.save(subjectMapper.modelToEntity(subject)));

    }

    public Subject updateSubject(String code, Subject subject) {
        var entity = subjectMapper.modelToEntity(subject);
        return subjectMapper.entityToModel(subjectRepository.save(entity));

    }

    public void deleteSubject(String code) {
        var entity = subjectRepository.findByCode(code);
        subjectRepository.delete(entity);
    }
}
