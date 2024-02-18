package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.PostDTO;
import com.freeuni.coursewhisperer.data.entity.PostEntity;
import com.freeuni.coursewhisperer.data.model.Post;
import com.freeuni.coursewhisperer.service.UserService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserService.class)
public interface PostMapper extends AbstractMapper<PostEntity, Post, PostDTO> {
}
