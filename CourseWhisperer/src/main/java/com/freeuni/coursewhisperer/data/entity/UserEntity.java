package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import com.freeuni.coursewhisperer.data.enums.EUserStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user", schema = "public")
public class UserEntity extends AbstractIdTimestampEntity {
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EUserStatus status;

}
