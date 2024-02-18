package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.PrerequisiteDTO;
import com.freeuni.coursewhisperer.data.model.Prerequisite;
import com.freeuni.coursewhisperer.service.PrerequisiteService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrerequisiteMapper extends AbstractMapper<Prerequisite, Prerequisite, PrerequisiteDTO> {

}