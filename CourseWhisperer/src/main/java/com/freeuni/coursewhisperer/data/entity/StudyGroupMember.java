package com.freeuni.coursewhisperer.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "study_group_members", schema = "public")
public class StudyGroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_group_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_study_group_members_study_group"))
    private StudyGroup studyGroup;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_study_group_members_member"))
    private User member;

}