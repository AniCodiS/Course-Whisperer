package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.CommentDTO;
import com.freeuni.coursewhisperer.data.entity.CommentEntity;
import com.freeuni.coursewhisperer.data.mapper.CommentMapper;
import com.freeuni.coursewhisperer.data.model.Comment;
import com.freeuni.coursewhisperer.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends AbstractService<CommentEntity, Long, Comment, CommentDTO> {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository,
                          CommentMapper commentMapper) {
        super(commentRepository, commentMapper, CommentEntity.class);
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public void createComment(Comment comment) {
        var entity = commentMapper.modelToEntity(comment);
        commentRepository.save(entity);
    }

    public List<Comment> getComments(Long postId) {
        return commentRepository.getAllByPost(postId).stream().map(commentMapper::entityToModel).toList();
    }

    public Comment updateComment(Long id, String content) {
        var entity = commentRepository.findById(id).get();
        entity.setContent(content);
        return commentMapper.entityToModel(commentRepository.save(entity));
    }

    public void deleteComment(Long id) {
        var entity = commentRepository.findById(id).get();
        commentRepository.delete(entity);
    }
}
