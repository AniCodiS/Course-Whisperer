package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prerequisites", schema = "public")
public class PrerequisiteEntity extends AbstractIdTimestampEntity {

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_prerequisites_subject"))
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "prerequisite_subject_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_prerequisites_prerequisite_subject"))
    private SubjectEntity prerequisiteSubject;

}