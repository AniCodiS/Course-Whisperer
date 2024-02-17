package com.freeuni.coursewhisperer.data.entity;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdTimestampEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "study_group_members", schema = "public")
public class StudyGroupMemberEntity extends AbstractIdTimestampEntity {

    @ManyToOne
    @JoinColumn(name = "study_group_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_study_group_members_study_group"))
    private StudyGroupEntity studyGroup;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_study_group_members_member"))
    private UserEntity member;

}