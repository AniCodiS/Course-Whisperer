package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.enums.EPostType;
import com.freeuni.coursewhisperer.data.mapper.PostMapper;
import com.freeuni.coursewhisperer.data.model.Comment;
import com.freeuni.coursewhisperer.data.model.Post;
import com.freeuni.coursewhisperer.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository,
                       CommentService commentService,
                       PostMapper postMapper) {
        this.postRepository = postRepository;
        this.commentService = commentService;
        this.postMapper = postMapper;
    }

    public List<Post> getPosts() {
        return postRepository.getPosts().stream().map(postMapper::entityToModel).toList();
    }

    public Post getPost(Long postId) {
        return postMapper.entityToModel(postRepository.findById(postId).get());
    }

    public void createPost(Post post) {
        var postEntity = postMapper.modelToEntity(post);
        postRepository.save(postEntity);
    }

    public List<Post> searchPosts(Long student, Long subject, EPostType type) {
        var res = postRepository.searchPosts(student, subject, type);
        return res.stream().map(postMapper::entityToModel).toList();
    }

    public Post updatePost(Long id, String content) {
        var postEntity = postRepository.findById(id).get();
        postEntity.setContent(content);
        return postMapper.entityToModel(postRepository.save(postEntity));
    }

    public void deletePost(Long id) {
        commentService.getComments(id).forEach(comment -> {
            commentService.deleteComment(comment.getId());
        });
        postRepository.deleteById(id);
    }

    public void addComment(Long id, Long user, String comment) {
        var postEntity = postMapper.entityToModel(postRepository.findById(id).get());
        commentService.createComment(Comment.builder()
                .post(postEntity)
                .user(user)
                .content(comment)
                .build());
    }
}
