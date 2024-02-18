package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.UserScoreDTO;
import com.freeuni.coursewhisperer.data.entity.UserScoreEntity;
import com.freeuni.coursewhisperer.data.model.UserScore;
import com.freeuni.coursewhisperer.service.UserScoreService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserScoreMapper extends AbstractMapper<UserScoreEntity, UserScore, UserScoreDTO> {

}