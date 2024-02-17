package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prerequisites", schema = "public")
public class PrerequisiteEntity extends AbstractIdTimestampEntity {
    @Column(name = "subject")
    private Long subject;

    @Column(name = "subject_code")
    private String prerequisite;
}