package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.PersonalInformationResponse;
import com.freeuni.coursewhisperer.data.entity.PersonalInformationEntity;
import com.freeuni.coursewhisperer.data.model.PersonalInformation;
import com.freeuni.coursewhisperer.service.PersonalInformationService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PersonalInformationService.class)
public interface PersonalInformationResponseMapper extends AbstractMapper<PersonalInformationEntity, PersonalInformation, PersonalInformationResponse> {

}