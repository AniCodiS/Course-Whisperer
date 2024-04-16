package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import com.freeuni.coursewhisperer.data.enums.EPostType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class PostEntity extends AbstractIdTimestampEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "up_vote")
    @Builder.Default()
    private Integer upVote = 0;

    @Column(name = "up_voters")
    private String upVoters = "";

    @Column(name = "down_vote")
    @Builder.Default()
    private Integer downVote = 0;

    @Column(name = "down_voters")
    private String downVoters = "";

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private EPostType type;

}