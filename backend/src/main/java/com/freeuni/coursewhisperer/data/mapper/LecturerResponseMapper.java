package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.LecturerResponse;
import com.freeuni.coursewhisperer.data.entity.LecturerEntity;
import com.freeuni.coursewhisperer.data.model.Lecturer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LecturerResponseMapper extends AbstractMapper<LecturerEntity, Lecturer, LecturerResponse> {

}