package com.freeuni.coursewhisperer.model.db;

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
@Table(name = "prerequisites")
public class Prerequisite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_prerequisites_subject"))
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "prerequisite_subject_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_prerequisites_prerequisite_subject"))
    private Subject prerequisiteSubject;

}