package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.entity.PersonalInformationEntity;
import com.freeuni.coursewhisperer.data.model.PersonalInformation;
import com.freeuni.coursewhisperer.service.PersonalInformationService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonalInformationMapper extends AbstractMapper<PersonalInformationEntity, PersonalInformation, PersonalInformationDTO> {

}