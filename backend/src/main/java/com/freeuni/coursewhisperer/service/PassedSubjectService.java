package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PassedSubjectDTO;
import com.freeuni.coursewhisperer.data.entity.PassedSubjectEntity;
import com.freeuni.coursewhisperer.data.enums.EGrade;
import com.freeuni.coursewhisperer.data.model.PassedSubject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PassedSubjectService extends AbstractService<PassedSubjectEntity, Long, PassedSubject, PassedSubjectDTO> {

    private final PassedSubjectRepository passedSubjectRepository;
    private final PassedSubjectMapper passedSubjectMapper;

    public PassedSubjectService(PassedSubjectRepository passedSubjectRepository,
                                PassedSubjectMapper passedSubjectMapper) {
        super(passedSubjectRepository, passedSubjectMapper, PassedSubjectEntity.class);
        this.passedSubjectRepository = passedSubjectRepository;
        this.passedSubjectMapper = passedSubjectMapper;
    }

    public List<String> getByUser(Long userId) {
        return passedSubjectRepository.findByStudentAndGrade(userId, EGrade.F);
    }
}
