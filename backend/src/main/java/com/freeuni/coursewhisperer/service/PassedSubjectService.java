//package com.freeuni.coursewhisperer.service;
//
//import com.freeuni.coursewhisperer.data.mapper.PassedSubjectMapper;
//import com.freeuni.coursewhisperer.data.api.PassedSubjectDto;
//import com.freeuni.coursewhisperer.data.entity.PassedSubjectEntity;
//import com.freeuni.coursewhisperer.data.model.PassedSubject;
//import com.freeuni.coursewhisperer.repository.PassedSubjectRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class PassedSubjectService extends AbstractService<PassedSubjectEntity, Long, PassedSubject, PassedSubjectDto>{
//
//    private final PassedSubjectRepository passedSubjectRepository;
//    private final PassedSubjectMapper passedSubjectMapper;
//
//    public PassedSubjectService(PassedSubjectRepository passedSubjectRepository,
//                                PassedSubjectMapper passedSubjectMapper) {
//        super(passedSubjectRepository, passedSubjectMapper, PassedSubjectEntity.class);
//        this.passedSubjectRepository = passedSubjectRepository;
//        this.passedSubjectMapper = passedSubjectMapper;
//    }
//}
