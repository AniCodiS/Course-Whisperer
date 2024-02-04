package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post")
public class PostEntity extends AbstractIdTimestampEntity {
    @OneToOne
    @JoinColumn(name = "student")
    private Long student;

    @OneToOne
    @JoinColumn(name = "subject")
    private SubjectEntity subject;

    @Column(name = "content")
    private String content;
}