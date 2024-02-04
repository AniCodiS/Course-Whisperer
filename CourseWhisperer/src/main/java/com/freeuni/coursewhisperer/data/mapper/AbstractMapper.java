package com.freeuni.coursewhisperer.data.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AbstractMapper<E, M, D> {

    @Named(value = "entityToModel")
    M entityToModel(E e);

    @Named(value = "modelToEntity")
    E modelToEntity(M m);

    @Named(value = "modelToDto")
    D modelToDto(M m);

    @Named(value = "dtoToModel")
    M dtoToModel(D d);

    @IterableMapping(qualifiedByName = "entityToModel")
    List<M> entityToModel(List<E> e);

    @IterableMapping(qualifiedByName = "modelToEntity")
    List<E> modelToEntity(List<M> m);

}
