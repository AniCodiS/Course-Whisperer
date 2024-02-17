package com.freeuni.coursewhisperer.data.api.dto;

import com.freeuni.coursewhisperer.data.enums.EPostType;

public record PostDTO (
         Long student,
         Long subject,
         String content,
         Integer upVote,
         Integer downVote,
         EPostType type
) {
}
