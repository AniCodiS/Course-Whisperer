package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.LecturerDTO;
import com.freeuni.coursewhisperer.data.entity.LecturerEntity;
import com.freeuni.coursewhisperer.data.model.Lecturer;
import com.freeuni.coursewhisperer.service.LecturerService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LecturerService.class)
public interface LecturerMapper extends AbstractMapper<LecturerEntity, Lecturer, LecturerDTO> {

}