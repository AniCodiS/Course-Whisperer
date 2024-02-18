package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.StudyGroupDTO;
import com.freeuni.coursewhisperer.data.entity.StudyGroupEntity;
import com.freeuni.coursewhisperer.data.model.StudyGroup;
import com.freeuni.coursewhisperer.service.StudyGroupService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = StudyGroupService.class)
public interface StudyGroupMapper extends AbstractMapper<StudyGroupEntity, StudyGroup, StudyGroupDTO> {

}