package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.UserDTO;
import com.freeuni.coursewhisperer.data.entity.User;
import com.freeuni.coursewhisperer.service.UserService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserService.class)
public interface UserMapper extends AbstractMapper<User, User, UserDTO> {

}