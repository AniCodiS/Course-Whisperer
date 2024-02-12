package com.freeuni.coursewhisperer.data.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractIdTimestampEntity extends AbstractIdEntity {

    @CreationTimestamp
    @Column(name = "created_at",
            nullable = false,
            updatable = false)
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    protected LocalDateTime modifiedAt;

}
