package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.SubjectDTO;
import com.freeuni.coursewhisperer.data.entity.SubjectEntity;
import com.freeuni.coursewhisperer.data.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends AbstractMapper<SubjectEntity, Subject, SubjectDTO> {
    @Override
    @Named("entityToModel")
    Subject entityToModel(SubjectEntity subjectEntity);

    @Override

    @Named(value = "modelToEntity")
    SubjectEntity modelToEntity(Subject subject);

    @Override
    @Named(value = "modelToDto")
    SubjectDTO modelToDto(Subject subject);

    @Named(value = "dtoToModel")
    Subject modelToDto(SubjectDTO subjectDTO);
}
