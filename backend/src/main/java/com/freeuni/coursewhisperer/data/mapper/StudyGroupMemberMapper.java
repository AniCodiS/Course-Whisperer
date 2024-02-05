package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.StudyGroupMemberDTO;
import com.freeuni.coursewhisperer.data.entity.StudyGroupMember;
import com.freeuni.coursewhisperer.service.StudyGroupMemberService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = StudyGroupMemberService.class)
public interface StudyGroupMemberMapper extends AbstractMapper<StudyGroupMember, StudyGroupMember, StudyGroupMemberDTO> {

}