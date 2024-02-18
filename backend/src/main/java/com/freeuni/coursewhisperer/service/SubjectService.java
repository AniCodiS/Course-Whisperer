package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.SubjectDTO;
import com.freeuni.coursewhisperer.data.entity.SubjectEntity;
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
public class SubjectService extends AbstractService<SubjectEntity, Long, Subject, SubjectDTO> {

    private final SubjectRepository subjectRepository;
    private final PassedSubjectService passedSubjectService;
    private final PrerequisiteService prerequisiteService;
    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository,
                          PassedSubjectService passedSubjectService,
                          PrerequisiteService prerequisiteService,
                          SubjectMapper subjectMapper) {
        super(subjectRepository, subjectMapper, SubjectEntity.class);
        this.subjectRepository = subjectRepository;
        this.passedSubjectService = passedSubjectService;
        this.prerequisiteService = prerequisiteService;
        this.subjectMapper = subjectMapper;
    }

    public List<Subject> search(String name, String code, ESchool schoolName,
                                Integer creditScore, Long lecturer, ESemester semester) {
        return subjectRepository.search(name, code, schoolName, creditScore, lecturer, semester).
                stream().map(subjectMapper::entityToModel).toList();
    }

    public List<Subject> chooseSubject(String username, Integer creditScore, ESchool schoolName, ESemester semester) {
        var passedSubjects = passedSubjectService.getPassesSubjects(username);
        var prerequisites = prerequisiteService.getAllPrerequisites().stream().collect(Collectors.groupingBy(
                Prerequisite::getSubject,
                Collectors.mapping(Prerequisite::getPrerequisite, Collectors.toList())
        ));

        List<Subject> subjects = new ArrayList<>();

        subjectRepository.search(null, null, schoolName, creditScore, null, semester).
                stream().map(subjectMapper::entityToModel).forEach(subject -> {
                            if (!passedSubjects.contains(subject.getCode()) &&
                                    new HashSet<>(passedSubjects).containsAll(prerequisites.get(subject).
                                            stream().map(Subject::getCode).toList())) {
                                subjects.add(subject);
                            }
                        }
                );
        return subjects;
    }
}
