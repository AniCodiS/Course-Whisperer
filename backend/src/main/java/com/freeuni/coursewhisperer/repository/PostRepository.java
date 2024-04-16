package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.PostEntity;
import com.freeuni.coursewhisperer.data.enums.EPostType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends AbstractRepository<PostEntity, Long> {
    @Query("select p from PostEntity p order by p.upVote desc ")
    List<PostEntity> getPosts();

    Optional<PostEntity> findById(Long id);

    @Query("select p from PostEntity p where " +
            "(:username is null or p.username =:username) and " +
            "(:subject is null or lower(p.subject) like lower( concat('%',  :subject, '%'))) and " +
            "(:type is null or p.type = :type) order by p.upVote desc ")
    List<PostEntity> searchPosts(String username, String subject, EPostType type);

    void deleteById(Long id);
}
