package com.freeuni.coursewhisperer.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subjects", schema = "public")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "credit_score")
    private Integer creditScore;

    @ManyToOne
    @JoinColumn(name = "taught_by", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_subjects_lecturer"))
    private Lecturer taughtBy;

    @Column(name = "semester")
    private Integer semester;

}