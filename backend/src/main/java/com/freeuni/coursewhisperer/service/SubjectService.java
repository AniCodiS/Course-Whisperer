package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.enums.ESchool;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import com.freeuni.coursewhisperer.data.mapper.SubjectMapper;
import com.freeuni.coursewhisperer.data.model.Prerequisite;
import com.freeuni.coursewhisperer.data.model.Subject;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final PassedSubjectService passedSubjectService;
    private final PrerequisiteService prerequisiteService;
    private final LecturerService lecturerService;
    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository,
                          PassedSubjectService passedSubjectService,
                          PrerequisiteService prerequisiteService,
                          LecturerService lecturerService, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.passedSubjectService = passedSubjectService;
        this.prerequisiteService = prerequisiteService;
        this.lecturerService = lecturerService;
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

    public synchronized Subject createSubject(Subject subject) {
        var lecturers =  Arrays.asList(subject.getLecturer().replace(" ", "").split(","));
        if (lecturerService.search(lecturers).size() < lecturers.size()) {
            throw ExceptionFactory.parametersAreNotValid();
        }
        return subjectMapper.entityToModel(subjectRepository.save(subjectMapper.modelToEntity(subject)));
    }

    public synchronized Subject updateSubject(String code, Subject subject) {
        var entity = subjectRepository.findByCode(code);
        entity.setName(subject.getName() != null ? subject.getName() : entity.getName());
        entity.setSchoolName(subject.getSchoolName() != null ? subject.getSchoolName() : entity.getSchoolName());
        entity.setCreditScore(subject.getCreditScore() != null ? subject.getCreditScore() : entity.getCreditScore());
        entity.setLecturer(subject.getLecturer() != null ? subject.getLecturer() : entity.getLecturer());
        entity.setSemester(subject.getSemester() != null ? subject.getSemester() : entity.getSemester());
        return subjectMapper.entityToModel(subjectRepository.save(entity));

    }

    public synchronized void deleteSubject(String code) {
        var entity = subjectRepository.findByCode(code);
        subjectRepository.delete(entity);
    }
}
