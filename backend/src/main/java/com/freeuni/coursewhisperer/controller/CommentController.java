package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.CommentDTO;
import com.freeuni.coursewhisperer.data.entity.CommentEntity;
import com.freeuni.coursewhisperer.data.mapper.CommentMapper;
import com.freeuni.coursewhisperer.data.model.Comment;
import com.freeuni.coursewhisperer.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/comment")
public class CommentController extends AbstractController<CommentEntity, Long, Comment, CommentDTO> {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService,
                             CommentMapper commentMapper) {
        super(commentService, commentMapper);
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> addComment(@RequestBody @Valid CommentDTO commentDTO) {
        commentService.createComment(commentMapper.dtoToModel(commentDTO));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}/all")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long postId) {
        var res = commentService.getComments(postId).stream().
                map(commentMapper::modelToDto).toList();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id,
                                                    @RequestBody @Valid CommentDTO commentDTO) {
        var res = commentMapper.modelToDto(commentService.updateComment(id, commentDTO.content()));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
