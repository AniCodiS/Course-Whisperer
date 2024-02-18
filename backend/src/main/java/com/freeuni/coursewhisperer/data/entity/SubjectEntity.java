package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import com.freeuni.coursewhisperer.data.enums.ESchool;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class SubjectEntity extends AbstractIdTimestampEntity {
    @Column(name = "subject_name")
    private String name;

    @Column(name = "subject_code")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "school_name")
    private ESchool schoolName;

    @Column(name = "credit_score")
    private Integer creditScore;

    @Column(name = "lecturer")
    private String lecturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester", nullable = false)
    private ESemester semester;

}
