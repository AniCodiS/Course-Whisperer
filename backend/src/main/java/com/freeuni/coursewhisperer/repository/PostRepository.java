package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.PostEntity;
import com.freeuni.coursewhisperer.data.enums.EPostType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends AbstractRepository<PostEntity, Long>{
    @Query("select p from PostEntity p order by p.upVote desc ")
    List<PostEntity> getPosts();

    Optional<PostEntity> findById(Long id);

    @Query("select p from PostEntity p where " +
            "(:student is null or p.student =:student) and " +
            "(:subject is null or p.subject = :subject) and " +
            "(:type is null or p.type = :type) order by p.upVote desc ")

    List<PostEntity> searchPosts(Long student, Long subject, EPostType type);

    void deleteById(Long id);
}
