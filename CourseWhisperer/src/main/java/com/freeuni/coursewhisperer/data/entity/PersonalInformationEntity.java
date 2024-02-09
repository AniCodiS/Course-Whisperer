package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "personal_information", schema = "public")
public class PersonalInformationEntity extends AbstractIdTimestampEntity {

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", foreignKey = @ForeignKey(name = "fk_personal_information_user"))
    private UserEntity user;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "year")
    private Integer year;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "email", nullable = false)
    private String email;

}