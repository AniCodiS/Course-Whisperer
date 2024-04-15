package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.PassedSubjectDTO;
import com.freeuni.coursewhisperer.data.entity.PassedSubjectEntity;
import com.freeuni.coursewhisperer.data.enums.EGrade;
import com.freeuni.coursewhisperer.data.mapper.PassedSubjectMapper;
import com.freeuni.coursewhisperer.data.model.PassedSubject;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.PassedSubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PassedSubjectService extends AbstractService<PassedSubjectEntity, Long, PassedSubject, PassedSubjectDTO> {

    private final PassedSubjectRepository passedSubjectRepository;
    private final PassedSubjectMapper passedSubjectMapper;
    private final CacheManager cacheManager;


    public PassedSubjectService(PassedSubjectRepository passedSubjectRepository,
                                PassedSubjectMapper passedSubjectMapper, CacheManager cacheManager) {
        super(passedSubjectRepository, passedSubjectMapper, PassedSubjectEntity.class);
        this.passedSubjectRepository = passedSubjectRepository;
        this.passedSubjectMapper = passedSubjectMapper;
        this.cacheManager = cacheManager;
    }

    public List<PassedSubject> getPassesSubjects(String username) {
        return passedSubjectMapper.entityToModel(passedSubjectRepository.findByUsernameAndGrade(username, EGrade.F));
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
        var cache = cacheManager.getCache("subjects");
        if (cache == null) {
            throw ExceptionFactory.resourceNotFound(Cache.class, "subjects");
        }
        var lst = passedSubjectRepository.findByUsernameOrderByGradeScoreAsc(username);
        lst.forEach(
                subject -> subject.setSubject(cache.get(subject.getSubject()).get().toString())
        );
        return lst.stream().map(passedSubjectMapper::entityToModel).toList();
    }

    public PassedSubject createPassedSubject(PassedSubject passedSubject) {
        var cache = cacheManager.getCache("subjects");
        if (cache.get(passedSubject.getSubject()) == null) {
            throw ExceptionFactory.resourceNotFound(PassedSubject.class, passedSubject.getSubject());
        }
        passedSubject.setGrade(convertToEGrade(passedSubject.getGradeScore()));
        return passedSubjectMapper.entityToModel(passedSubjectRepository.save(passedSubjectMapper.modelToEntity(passedSubject)));
    }

    private EGrade convertToEGrade(Integer numericalGrade) {
        if (numericalGrade >= 91) {
            return EGrade.A;
        } else if (numericalGrade >= 81) {
            return EGrade.B;
        } else if (numericalGrade >= 71) {
            return EGrade.C;
        } else if (numericalGrade >= 61) {
            return EGrade.D;
        } else if (numericalGrade >= 51) {
            return EGrade.E;
        } else {
            return EGrade.F;
        }
    }
}
