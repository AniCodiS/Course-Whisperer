package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.PassedSubjectDTO;
import com.freeuni.coursewhisperer.data.entity.PassedSubjectEntity;
import com.freeuni.coursewhisperer.data.model.PassedSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PassedSubjectMapper extends AbstractMapper<PassedSubjectEntity, PassedSubject, PassedSubjectDTO> {
    @Override
    @Named("entityToModel")
    PassedSubject entityToModel(PassedSubjectEntity passedSubjectEntity);

    @Override

    @Named(value = "modelToEntity")
    PassedSubjectEntity modelToEntity(PassedSubject passedSubject);

    @Override
    @Named(value = "modelToDto")
    PassedSubjectDTO modelToDto(PassedSubject passedSubject);

    @Named(value = "dtoToModel")
    PassedSubject modelToDto(PassedSubjectDTO passedSubjectDTO);
}
