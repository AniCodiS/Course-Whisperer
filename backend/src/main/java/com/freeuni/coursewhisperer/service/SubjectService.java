package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.enums.ESchool;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import com.freeuni.coursewhisperer.data.mapper.SubjectMapper;
import com.freeuni.coursewhisperer.data.model.PassedSubject;
import com.freeuni.coursewhisperer.data.model.Prerequisite;
import com.freeuni.coursewhisperer.data.model.Subject;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.SubjectRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@CacheConfig(cacheNames = "subjects")
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final PassedSubjectService passedSubjectService;
    private final PrerequisiteService prerequisiteService;
    private final LecturerService lecturerService;
    private final SubjectMapper subjectMapper;
    private final CacheManager cacheManager;


    public SubjectService(SubjectRepository subjectRepository,
                          PassedSubjectService passedSubjectService,
                          PrerequisiteService prerequisiteService,
                          LecturerService lecturerService, SubjectMapper subjectMapper,
                          CacheManager cacheManager) {
        this.subjectRepository = subjectRepository;
        this.passedSubjectService = passedSubjectService;
        this.prerequisiteService = prerequisiteService;
        this.lecturerService = lecturerService;
        this.subjectMapper = subjectMapper;
        this.cacheManager = cacheManager;
    }

    @PostConstruct
    private void initCache() {
        updateSubjects();
    }

    public void updateSubjects() {
        var cache = cacheManager.getCache("subjects");
        if (cache == null) {
            log.error("Subjects cache could not be found");
            return;
        }
        cache.clear();
        getAllSubjects().forEach(subject -> {
            try {
                cache.put(subject.getCode(), subject);
            } catch (Exception e) {
                log.warn("Could not fill dictionary cache for {}", subject.getCode());
            }
        });
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll().stream().map(subjectMapper::entityToModel).toList();
    }

    public List<Subject> search(String name, String code, ESchool schoolName,
                                Integer creditScore, String lecturer, ESemester semester) {
        return subjectRepository.search(name, code, schoolName, creditScore, lecturer, semester).
                stream().map(subjectMapper::entityToModel).toList();
    }

    public List<Subject> chooseSubject(String username, ESchool schoolName, Integer creditScore, ESemester semester) {
        var passedSubjects = passedSubjectService.getPassesSubjects(username).stream().map(PassedSubject::getSubject).toList();
        var prerequisites = prerequisiteService.getAllPrerequisites().stream().collect(Collectors.groupingBy(
                Prerequisite::getSubject,
                Collectors.mapping(Prerequisite::getPrerequisite, Collectors.toList())
        ));

        List<Subject> subjects = new ArrayList<>();

        subjectRepository.search(null, null, schoolName, creditScore, null, semester).stream().
                map(subjectMapper::entityToModel).forEach(subject -> {
                            if (!passedSubjects.contains(subject.getCode()) && (prerequisites.get(subject.getCode()) == null ||
                                    prerequisites.get(subject.getCode()).isEmpty() ||
                                    new HashSet<>(passedSubjects).containsAll(prerequisites.get(subject.getCode())))) {
                                subjects.add(subject);
                            }
                        }
                );
        return subjects;
    }

    @Cacheable(value = "subjects", key = "#subject.code")
    public synchronized Subject createSubject(Subject subject) {
        var lecturers = Arrays.asList(subject.getLecturer().replace(" ", "").split(","));
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
