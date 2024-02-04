package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.PassedSubjectDto;
import com.freeuni.coursewhisperer.data.entity.PassedSubjectEntity;
import com.freeuni.coursewhisperer.data.model.PassedSubject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassedSubjectMapper extends AbstractMapper<PassedSubjectEntity, PassedSubject, PassedSubjectDto> {
}
