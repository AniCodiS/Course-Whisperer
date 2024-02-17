package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends AbstractRepository<PostEntity, Long>{
    @Query("select p from PostEntity p order by p.upVote desc ")
    List<PostEntity> getPosts();

    Optional<PostEntity> findById(Long id);
}
