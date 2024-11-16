package com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="STUDY_GROUP_BOARD_COMMENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyGroupBoardComment {

    @Id
    @Column(name="STUDY_GROUP_BOARD_COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyGroupBoardCommentId;

    @Column(name="CONTENT")
    private String content;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name="UPDATED_AT")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name="ACTIVE_STATUS")
    private StudyGroupBoardCommentStatus activeStatus;

    @Column(name="MEMBER_ID")
    private Long memberId;

    @Column(name="STUDY_GROUP_BOARD_ID")
    private Long studyGroupBoardId;

}
