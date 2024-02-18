package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.UserResponse;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import com.freeuni.coursewhisperer.data.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper extends AbstractMapper<UserEntity, User, UserResponse> {

}