package com.freeuni.coursewhisperer.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "study_groups", schema = "public")
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "meeting_time")
    private LocalDateTime meetingTime;

    @Column(name = "group_name", nullable = false)
    private String groupName;

}