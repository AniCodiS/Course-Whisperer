package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.CommentEntity;

import java.util.List;

public interface CommentRepository extends AbstractRepository<CommentEntity, Long> {
    List<CommentEntity> getAllByPostId(Long postId);
}
