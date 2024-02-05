package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.LecturerDTO;
import com.freeuni.coursewhisperer.data.entity.Lecturer;
import com.freeuni.coursewhisperer.service.LecturerService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LecturerService.class)
public interface LecturerMapper extends AbstractMapper<Lecturer, Lecturer, LecturerDTO> {

}