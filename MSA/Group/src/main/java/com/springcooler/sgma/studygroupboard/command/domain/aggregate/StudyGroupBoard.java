package com.springcooler.sgma.studygroupboard.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="STUDY_GROUP_BOARD")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyGroupBoard {

    @Id
    @Column(name="STUDY_GROUP_BOARD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyGroupBoardId;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT")
    private String content;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name="UPDATED_AT")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name="ACTIVE_STATUS")
    private StudyGroupBoardStatus activeStatus;

    @Column(name="LIKES")
    private Integer likes;

    @Column(name="MEMBER_ID")
    private Long memberId;

    @Column(name="GROUP_ID")
    private Long groupId;

}
