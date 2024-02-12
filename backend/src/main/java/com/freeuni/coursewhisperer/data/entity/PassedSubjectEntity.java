//package com.freeuni.coursewhisperer.data.entity;
//
//import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
//import com.freeuni.coursewhisperer.data.enums.EGrade;
//import javax.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "passed_subject")
//public class PassedSubjectEntity extends AbstractIdTimestampEntity {
//    @OneToOne // TODO change all onetoone
//    @JoinColumn(name = "student")
//    private Long student; // TODO change it
//
//    @OneToOne
//    @JoinColumn(name = "subject")
//    private SubjectEntity subject;
//
//    @Column(name = "grade")
//    private EGrade grade;
//
//    @Column(name = "grade_score")
//    private int gradeScore;
//}
