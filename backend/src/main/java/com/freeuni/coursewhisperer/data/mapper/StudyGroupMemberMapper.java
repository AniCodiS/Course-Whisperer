package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.entity.StudyGroupMemberEntity;
import com.freeuni.coursewhisperer.data.model.StudyGroupMember;
import com.freeuni.coursewhisperer.service.StudyGroupMemberService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudyGroupMemberMapper extends AbstractMapper<StudyGroupMemberEntity, StudyGroupMember, StudyGroupMemberDTO> {

}