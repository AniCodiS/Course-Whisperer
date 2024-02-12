package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdEntity;
import com.freeuni.coursewhisperer.data.mapper.AbstractMapper;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.AbstractRepository;
import jakarta.persistence.MappedSuperclass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

@Slf4j
@MappedSuperclass
public abstract class AbstractService<E extends AbstractIdEntity, ID extends Serializable, M, D> {

    final Class<E> clazz;
    final AbstractRepository<E, ID> repository;
    final AbstractMapper<E, M, D> mapper;
//    static final LockManager sharedLock = new LockManager("Abstract-Lock", true);

    public AbstractService(AbstractRepository<E, ID> repository, AbstractMapper<E, M, D> mapper, Class<E> clazz) {
        this.repository = repository;
        this.mapper = mapper;
        this.clazz = clazz;
    }

    protected M get(ID id) {
        E res = repository.findById(id).orElseThrow(() -> ExceptionFactory.resourceNotFound(clazz, id.toString()));
        return mapper.entityToModel(res);
    }

    public M save(M model) {
        E saved = repository.save(mapper.modelToEntity(model));
        return mapper.entityToModel(saved);
    }

    public List<M> save(List<? extends M> models) {
        var savedList = repository.saveAll(models.stream().map(mapper::modelToEntity).toList());
        return savedList.stream().map(mapper::entityToModel).toList();
    }

    public void delete(ID id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> {
                            throw ExceptionFactory.resourceNotFound(clazz, id.toString());
                        });
    }

    public List<M> all() {
        return mapper.entityToModel(repository.findAll());
    }

    public Page<M> list(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToModel);
    }

    public long countQuery() {
        return repository.count();
    }

}