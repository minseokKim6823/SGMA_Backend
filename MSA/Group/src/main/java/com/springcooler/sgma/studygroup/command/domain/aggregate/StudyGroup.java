package com.springcooler.sgma.studygroup.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="STUDY_GROUP")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyGroup {

    @Id
    @Column(name="GROUP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(name="GROUP_NAME")
    private String groupName;

    @Enumerated(EnumType.STRING)
    @Column(name="ACTIVE_STATUS")
    private StudyGroupStatus activeStatus;

    @Column(name="GROUP_MEMBERS")
    private Integer groupMembers;

    @Column(name="USER_ID")
    private Long userId;

    @Column(name="STUDY_GROUP_CATEGORY_ID")
    private Integer studyGroupCategoryId;

}
