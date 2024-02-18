package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.UpdatedUserDTO;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import com.freeuni.coursewhisperer.data.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdatedUserMapper extends AbstractMapper<UserEntity, User, UpdatedUserDTO> {

}