package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.UpdateUserDTO;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import com.freeuni.coursewhisperer.data.model.User;
import com.freeuni.coursewhisperer.service.UserService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper extends AbstractMapper<UserEntity, User, UpdateUserDTO> {

}