package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import com.freeuni.coursewhisperer.data.enums.EGrade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    @Column(name = "student")
    private Long student;

    @Column(name = "subject")
    private Long subject;

    @Column(name = "grade")
    private EGrade grade;

    @Column(name = "grade_score")
    private Integer gradeScore;
}
