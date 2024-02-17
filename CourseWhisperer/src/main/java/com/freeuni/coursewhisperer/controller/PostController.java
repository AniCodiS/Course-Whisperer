package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.PostDTO;
import com.freeuni.coursewhisperer.data.enums.EPostType;
import com.freeuni.coursewhisperer.data.mapper.PostMapper;
import com.freeuni.coursewhisperer.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService,
                          PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }


    @PostMapping("/create")
    public ResponseEntity<Void> addPost(@RequestBody PostDTO postDTO) {
        postService.createPost(postMapper.dtoToModel(postDTO));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getPosts() {
        var res = postService.getPosts().stream().map(postMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> getPosts(@RequestParam(required = false) Long student,
                                                  @RequestParam(required = false) Long subject,
                                                  @RequestParam(required = false) EPostType type) {
        var res = postService.searchPosts(student, subject, type).stream().map(postMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id,
                                              @RequestParam(required = false) String content) {
        var res = postMapper.modelToDto(postService.updatePost(id, content));
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> addComment(@PathVariable Long id,
                                              @RequestParam Long user,
                                              @RequestParam String comment) {
        postService.addComment(id, user, comment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
