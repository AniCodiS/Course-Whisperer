package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.StudyGroupResponse;
import com.freeuni.coursewhisperer.data.entity.StudyGroupEntity;
import com.freeuni.coursewhisperer.data.model.StudyGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudyGroupResponseMapper extends AbstractMapper<StudyGroupEntity, StudyGroup, StudyGroupResponse> {

}