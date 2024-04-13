package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.PostDTO;
import com.freeuni.coursewhisperer.data.entity.PostEntity;
import com.freeuni.coursewhisperer.data.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PostMapper extends AbstractMapper<PostEntity, Post, PostDTO> {
    @Override
    @Named("entityToModel")
    Post entityToModel(PostEntity postEntity);

    @Override

    @Named(value = "modelToEntity")
    PostEntity modelToEntity(Post post);

    @Override
    @Named(value = "modelToDto")
    PostDTO modelToDto(Post post);

    @Named(value = "dtoToModel")
    Post modelToDto(PostDTO postDTO);
}
