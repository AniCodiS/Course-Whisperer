package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.UserScoreResponse;
import com.freeuni.coursewhisperer.data.entity.UserScoreEntity;
import com.freeuni.coursewhisperer.data.model.UserScore;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserScoreResponseMapper extends AbstractMapper<UserScoreEntity, UserScore, UserScoreResponse> {

}