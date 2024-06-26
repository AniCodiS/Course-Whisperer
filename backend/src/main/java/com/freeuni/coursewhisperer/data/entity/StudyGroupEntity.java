package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "study_groups", schema = "public")
public class StudyGroupEntity extends AbstractIdTimestampEntity {

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "creator_username", nullable = false)
    private String creatorUsername;

    @Column(name = "meeting_time")
    private LocalDateTime meetingTime;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "current_member_count", nullable = false)
    private Integer currentMemberCount;

    @Column(name = "max_member_count", nullable = false)
    private Integer maxMemberCount;

}