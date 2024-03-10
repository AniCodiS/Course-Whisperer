package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.enums.EPostType;
import com.freeuni.coursewhisperer.data.enums.EPostVote;
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

    public void createPost(Post post) {
        var postEntity = postMapper.modelToEntity(post);
        postRepository.save(postEntity);
    }

    public List<Post> searchPosts(String username, String subject, EPostType type) {
        var res = postRepository.searchPosts(username, subject, type);
        return res.stream().map(postMapper::entityToModel).toList();
    }

    public Post updatePost(String username, Long id, String content) {
        var postEntity = postRepository.findById(id).get(); // TODO exception
        postEntity.setContent(content);
        return postMapper.entityToModel(postRepository.save(postEntity));
    }

    public synchronized Post updatePost(Long id, EPostVote vote) {
        var postEntity = postRepository.findById(id).get();
        if (vote == EPostVote.UPVOTE) {
            postEntity.setUpVote(postEntity.getUpVote() + 1);
        } else {
            postEntity.setDownVote(postEntity.getDownVote() + 1);
        }
        return postMapper.entityToModel(postRepository.save(postEntity));
    }

    public void deletePost(String username, Long id) { // TODO exception
        commentService.getComments(id).forEach(comment -> {
            commentService.deleteComment(username, comment.getId());
        });
        postRepository.deleteById(id);
    }

    public void addComment(Long id, String username, String comment) {
        var postEntity = postMapper.entityToModel(postRepository.findById(id).get());
        commentService.createComment(Comment.builder()
                .post(postEntity.getId())
                .username(username)
                .content(comment)
                .build());
    }
}
