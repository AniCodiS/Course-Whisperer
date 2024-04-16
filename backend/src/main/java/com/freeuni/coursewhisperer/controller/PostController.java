package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.CommentDTO;
import com.freeuni.coursewhisperer.data.api.dto.PostDTO;
import com.freeuni.coursewhisperer.data.enums.EPostType;
import com.freeuni.coursewhisperer.data.enums.EPostVote;
import com.freeuni.coursewhisperer.data.mapper.PostMapper;
import com.freeuni.coursewhisperer.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Validated
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService,
                          PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }


    @PostMapping("/create")
    public ResponseEntity<PostDTO> addPost(@RequestBody @Valid PostDTO postDTO) {
        var res = postService.createPost(postMapper.dtoToModel(postDTO));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getPosts() {
        var res = postService.getPosts().stream().map(postMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> getPosts(@RequestParam(required = false) String username,
                                                  @RequestParam(required = false) String subject,
                                                  @RequestParam(required = false) EPostType type) {
        var res = postService.searchPosts(username, subject, type).stream().map(postMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePostContent(@PathVariable Long id,
                                              @RequestParam String username,
                                              @RequestParam(required = false) String content) {
        var res = postMapper.modelToDto(postService.updatePost(username, id, content));
        return ResponseEntity.ok(res);
    }

    @PutMapping("/vote/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id,
                                              @RequestParam String username,
                                              @RequestParam String vote) {
        var res = postMapper.modelToDto(postService.updatePost(username, id, EPostVote.valueOf(vote)));
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long postId,
                                                 @RequestParam String username,
                                                 @RequestParam String comment) {
        var res = postService.addComment(postId, username, comment);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           @RequestParam String username) {
        postService.deletePost(username, id);
        return ResponseEntity.ok().build();
    }
}
