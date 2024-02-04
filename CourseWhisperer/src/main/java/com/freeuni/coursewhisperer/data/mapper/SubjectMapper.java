package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.SubjectDto;
import com.freeuni.coursewhisperer.data.entity.SubjectEntity;
import com.freeuni.coursewhisperer.data.model.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends AbstractMapper<SubjectEntity, Subject, SubjectDto> {
}
