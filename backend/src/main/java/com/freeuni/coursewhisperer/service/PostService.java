package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.CommentDTO;
import com.freeuni.coursewhisperer.data.api.dto.PostDTO;
import com.freeuni.coursewhisperer.data.enums.EPostType;
import com.freeuni.coursewhisperer.data.enums.EPostVote;
import com.freeuni.coursewhisperer.data.mapper.CommentMapper;
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

    private final CommentMapper commentMapper;

    public PostService(PostRepository postRepository,
                       CommentService commentService,
                       PostMapper postMapper, CommentMapper commentMapper) {
        this.postRepository = postRepository;
        this.commentService = commentService;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    public List<Post> getPosts() {
        return postRepository.getPosts().stream().map(postMapper::entityToModel).toList();
    }

    public List<Post> searchPosts(String username, String subject, EPostType type) {
        var res = postRepository.searchPosts(username, subject, type);
        return res.stream().map(postMapper::entityToModel).toList();
    }

    public synchronized PostDTO createPost(Post post) {
        post.setUpVote(0);
        post.setDownVote(0);
        var postEntity = postMapper.modelToEntity(post);
        var res = postRepository.save(postEntity);
        return postMapper.modelToDto(postMapper.entityToModel(res));
    }

    public synchronized Post updatePost(String username, Long id, String content) {
        var postEntity = postRepository.findById(id).get(); // TODO exception
        postEntity.setContent(content);
        return postMapper.entityToModel(postRepository.save(postEntity));
    }

    public synchronized Post updatePost(String username, Long id, EPostVote vote) {
        var postEntity = postRepository.findById(id).get();
        if (vote == EPostVote.UPVOTE) {
            if (postEntity.getUpVoters() != null && postEntity.getUpVoters().contains(username)) {
                postEntity.setUpVote(postEntity.getUpVote() - 1);
                postEntity.setUpVoters(postEntity.getUpVoters().replace("," + username, ""));
            } else {
                postEntity.setUpVote(postEntity.getUpVote() + 1);
                postEntity.setUpVoters(
                        postEntity.getUpVoters() == null ? "," + username :
                                postEntity.getUpVoters() + "," + username);
            }
        } else {
            if (postEntity.getUpVoters() != null  && postEntity.getDownVoters().contains(username)) {
                postEntity.setDownVote(postEntity.getDownVote() - 1);
                postEntity.setDownVoters(postEntity.getDownVoters().replace("," + username, ""));
            } else {
                postEntity.setDownVote(postEntity.getUpVote() + 1);
                postEntity.setDownVoters(
                        postEntity.getDownVoters() == null ? "," + username :
                                postEntity.getDownVoters() + "," + username);
            }
        }
        return postMapper.entityToModel(postRepository.save(postEntity));
    }

    public synchronized void deletePost(String username, Long id) { // TODO exception
        commentService.getComments(id).forEach(comment -> {
            commentService.deleteComment(username, comment.getId());
        });
        postRepository.deleteById(id);
    }

    public synchronized CommentDTO addComment(Long id, String username, String comment) {
        var postEntity = postMapper.entityToModel(postRepository.findById(id).get());
        return commentMapper.modelToDto(commentService.createComment(Comment.builder()
                .post(postEntity.getId())
                .username(username)
                .content(comment)
                .build()));
    }
}
