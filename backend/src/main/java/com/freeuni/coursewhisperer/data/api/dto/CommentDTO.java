package com.freeuni.coursewhisperer.data.api.dto;

import com.freeuni.coursewhisperer.data.validator.CorrectText;

public record CommentDTO(
        String username,
        Long postId,
        @CorrectText
        String content
) {
}
