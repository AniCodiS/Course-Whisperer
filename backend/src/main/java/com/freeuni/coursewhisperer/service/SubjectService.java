//package com.freeuni.coursewhisperer.service;
//
//import com.freeuni.coursewhisperer.data.mapper.SubjectMapper;
//import com.freeuni.coursewhisperer.data.api.SubjectDto;
//import com.freeuni.coursewhisperer.data.entity.SubjectEntity;
//import com.freeuni.coursewhisperer.data.model.Subject;
//import com.freeuni.coursewhisperer.repository.SubjectRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class SubjectService extends AbstractService<SubjectEntity, Long, Subject, SubjectDto> {
//
//    private final SubjectRepository subjectRepository;
//    private final SubjectMapper subjectMapper;
//
//    public SubjectService(SubjectRepository subjectRepository,
//                          SubjectMapper subjectMapper) {
//        super(subjectRepository, subjectMapper, SubjectEntity.class);
//        this.subjectRepository = subjectRepository;
//        this.subjectMapper = subjectMapper;
//    }
//}
