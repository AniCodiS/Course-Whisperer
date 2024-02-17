package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "study_groups", schema = "public")
public class StudyGroupEntity extends AbstractIdTimestampEntity {

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "meeting_time")
    private LocalDateTime meetingTime;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "member_amount")
    private Integer memberAmount;

    @Column(name = "max_member_amount")
    private Integer maxMemberAmount;

    @Column(name = "members")
    private String members;
}