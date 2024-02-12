package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractIdEntity, ID extends Serializable> extends
        JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}