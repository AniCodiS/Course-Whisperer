package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberResponse;
import com.freeuni.coursewhisperer.data.entity.StudyGroupMemberEntity;
import com.freeuni.coursewhisperer.data.model.StudyGroupMember;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudyGroupMemberResponseMapper extends AbstractMapper<StudyGroupMemberEntity, StudyGroupMember, StudyGroupMemberResponse> {

}
