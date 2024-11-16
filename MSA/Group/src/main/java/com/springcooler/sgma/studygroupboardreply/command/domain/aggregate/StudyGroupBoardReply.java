package com.springcooler.sgma.studygroupboardreply.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="STUDY_GROUP_BOARD_REPLY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyGroupBoardReply {

    @Id
    @Column(name="STUDY_GROUP_BOARD_REPLY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyGroupBoardReplyId;

    @Column(name="CONTENT")
    private String content;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name="UPDATED_AT")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name="ACTIVE_STATUS")
    private StudyGroupBoardReplyStatus activeStatus;

    @Column(name="MEMBER_ID")
    private Long memberId;

    @Column(name="STUDY_GROUP_BOARD_COMMENT_ID")
    private Long studyGroupBoardCommentId;

}
