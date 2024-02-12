//package com.freeuni.coursewhisperer.data.entity;
//
//import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
//import javax.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "comment")
//public class CommentEntity extends AbstractIdTimestampEntity {
//    @OneToOne
//    @JoinColumn(name = "user")
//    private Long user;
//
//    @ManyToOne
//    @JoinColumn(name = "post")
//    private PostEntity post;
//
//    @Column(name = "content")
//    private String content;
//}
