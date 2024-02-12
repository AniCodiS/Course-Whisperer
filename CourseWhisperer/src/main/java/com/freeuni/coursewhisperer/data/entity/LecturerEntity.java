package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "lecturers", schema = "public")
public class LecturerEntity extends AbstractIdTimestampEntity {

    @Column(name = "lecturer_name", nullable = false)
    private String lecturerName;

    @Column(name = "department")
    private String department;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

}
