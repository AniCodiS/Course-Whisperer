package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.PostDto;
import com.freeuni.coursewhisperer.data.entity.PostEntity;
import com.freeuni.coursewhisperer.data.model.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends AbstractMapper<PostEntity, Post, PostDto> {
}