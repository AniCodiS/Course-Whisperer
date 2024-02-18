package com.freeuni.coursewhisperer.data.api.dto;

import com.freeuni.coursewhisperer.data.enums.EPostType;
import com.freeuni.coursewhisperer.data.validator.CorrectText;

public record PostDTO(
        String username,
        String subject,
        @CorrectText
        String content,
        Integer upVote,
        Integer downVote,
        EPostType type
) {
}
