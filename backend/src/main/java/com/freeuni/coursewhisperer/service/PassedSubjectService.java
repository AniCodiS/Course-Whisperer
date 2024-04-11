package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PassedSubjectDTO;
import com.freeuni.coursewhisperer.data.entity.PassedSubjectEntity;
import com.freeuni.coursewhisperer.data.enums.EGrade;
import com.freeuni.coursewhisperer.data.mapper.PassedSubjectMapper;
import com.freeuni.coursewhisperer.data.model.PassedSubject;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.PassedSubjectRepository;
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

    public List<String> getPassesSubjects(String username) {
        return passedSubjectRepository.findByUsernameAndGrade(username, EGrade.F);
    }

    public void deletePassedSubject(String username, Long id) {
        var entity = passedSubjectRepository.findById(id);
        if (entity.isEmpty()) {
            throw ExceptionFactory.resourceNotFound(PassedSubjectEntity.class, id.toString());
        }
        if (!entity.get().getUsername().equals(username)) {
            throw ExceptionFactory.resourceIsNotYours();
        }
        passedSubjectRepository.delete(entity.get());
    }

    public PassedSubject updatePassedSubject(Long id, PassedSubject passedSubject) {
        var entity = passedSubjectRepository.findById(id);
        if (entity.isEmpty()) {
            throw ExceptionFactory.resourceNotFound(PassedSubjectEntity.class, id.toString());
        }
        if (!entity.get().getUsername().equals(passedSubject.getUsername())) {
            throw ExceptionFactory.resourceIsNotYours();
        }
        var newEntity = passedSubjectMapper.modelToEntity(passedSubject);
        newEntity.setId(entity.get().getId());
        return passedSubjectMapper.entityToModel(passedSubjectRepository.save(newEntity));
    }

    public List<PassedSubject> getPassedSubjects(String username) {
        return passedSubjectRepository.findByUsername(username).stream().map(passedSubjectMapper::entityToModel).toList();
    }

    public PassedSubject createPassedSubject(PassedSubject passedSubject) {
        return passedSubjectMapper.entityToModel(passedSubjectRepository.save(passedSubjectMapper.modelToEntity(passedSubject)));
    }
}
