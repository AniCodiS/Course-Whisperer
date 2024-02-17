package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationDTO;
import com.freeuni.coursewhisperer.data.entity.PersonalInformation;
import com.freeuni.coursewhisperer.service.PersonalInformationService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PersonalInformationService.class)
public interface PersonalInformationMapper extends AbstractMapper<PersonalInformation, PersonalInformation, PersonalInformationDTO> {

}