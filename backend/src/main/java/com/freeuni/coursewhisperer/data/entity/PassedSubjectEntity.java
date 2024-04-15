package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import com.freeuni.coursewhisperer.data.enums.EGrade;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passed_subject")
public class PassedSubjectEntity extends AbstractIdTimestampEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "subject")
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade")
    private EGrade grade;

    @Column(name = "grade_score")
    private Integer gradeScore;
}
