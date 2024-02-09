package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_scores", schema = "public")
public class UserScoreEntity extends AbstractIdTimestampEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "score")
    private int score;

}