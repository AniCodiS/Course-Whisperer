package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class SubjectEntity extends AbstractIdTimestampEntity {
    @Column(name = "subject_name")
    private String name;

    @Column(name = "credit_score")
    private int creditScore;

    @OneToOne
    @JoinColumn(name = "lecturer")
    private LecturerEntity lecturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester", nullable = false)
    private ESemester semester;

}
