package com.freeuni.coursewhisperer.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "prerequisites", schema = "public")
public class Prerequisite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_prerequisites_subject"))
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "prerequisite_subject_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_prerequisites_prerequisite_subject"))
    private Subject prerequisiteSubject;

}