package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "study_groups", schema = "public")
public class StudyGroupEntity extends AbstractIdTimestampEntity {

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "meeting_time")
    private LocalDateTime meetingTime;

    @Column(name = "group_name", nullable = false)
    private String groupName;

}