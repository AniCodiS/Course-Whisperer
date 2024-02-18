package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.CommentDTO;
import com.freeuni.coursewhisperer.data.entity.CommentEntity;
import com.freeuni.coursewhisperer.data.mapper.CommentMapper;
import com.freeuni.coursewhisperer.data.model.Comment;
import com.freeuni.coursewhisperer.exception.CourseWhispererException;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
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

    public Comment updateComment(Long id, Comment comment) {
        var entity = commentRepository.findById(id).get();
        if (!entity.getUsername().equals(comment.getUsername())) {
            throw ExceptionFactory.commentIsNotYours();
        }
        entity.setContent(comment.getContent());
        return commentMapper.entityToModel(commentRepository.save(entity));
    }

    public void deleteComment(String username, Long id) {
        var entity = commentRepository.findById(id).get();
        if (!entity.getUsername().equals(username)) {
            throw ExceptionFactory.commentIsNotYours();
        }
        commentRepository.delete(entity);
    }
}
