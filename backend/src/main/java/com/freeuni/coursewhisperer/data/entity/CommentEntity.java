package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class CommentEntity extends AbstractIdTimestampEntity {
    @Column(name = "user")
    private String username;

    @Column(name = "post")
    private Long postId;

    @Column(name = "content")
    private String content;
}
