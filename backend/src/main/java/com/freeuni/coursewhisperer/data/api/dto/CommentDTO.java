package com.freeuni.coursewhisperer.data.api.dto;

public record CommentDTO(
        UserDTO user,
        PostDTO post,
        String content
) {
}
