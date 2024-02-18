package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.CommentDTO;
import com.freeuni.coursewhisperer.data.entity.CommentEntity;
import com.freeuni.coursewhisperer.data.model.Comment;
import com.freeuni.coursewhisperer.service.PostService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {PostService.class})
public interface CommentMapper extends AbstractMapper<CommentEntity, Comment, CommentDTO> {

    @Override
    @Named("entityToModel")
    @Mapping(source = "postId", target = "post")
    Comment entityToModel(CommentEntity commentEntity);

    @Override
    @Named("modelToEntity")
    @Mapping(source = "post", target = "postId")
    CommentEntity modelToEntity(Comment comment);

    @Override
    @Named("dtoToModel")
    @Mapping(source = "postId", target = "post")
    Comment dtoToModel(CommentDTO commentDTO);

    @Override
    @Named("modelToDto")
    @Mapping(source = "post", target = "postId")
    CommentDTO modelToDto(Comment comment);
}
