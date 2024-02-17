package com.freeuni.coursewhisperer.data.api.dto;

import com.freeuni.coursewhisperer.data.validator.CorrectText;

public record CommentDTO(
        UserDTO user,
        PostDTO post,
        @CorrectText
        String content
) {
}
