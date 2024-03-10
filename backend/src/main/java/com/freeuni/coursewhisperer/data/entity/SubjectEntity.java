package com.freeuni.coursewhisperer.data.entity;

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
public class SubjectEntity {
    @Id
    @Column(name = "subject_code", nullable = false, unique = true)
    private String code;

    @Column(name = "subject_name")
    private String name;

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
