package com.freeuni.coursewhisperer.data.mapper;

import com.freeuni.coursewhisperer.data.api.dto.CommentDTO;
import com.freeuni.coursewhisperer.data.entity.CommentEntity;
import com.freeuni.coursewhisperer.data.model.Comment;
import com.freeuni.coursewhisperer.service.PostService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PostService.class)
public interface CommentMapper extends AbstractMapper<CommentEntity, Comment, CommentDTO> {
}
