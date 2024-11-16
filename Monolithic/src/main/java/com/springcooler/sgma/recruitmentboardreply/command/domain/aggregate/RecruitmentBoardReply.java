package com.springcooler.sgma.recruitmentboardreply.command.domain.aggregate;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RecruitmentBoardReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recruitment_board_reply_id",length = 20)
    private Long recruitmentBoardReplyId;

    @Column(name="content",nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "active_status",nullable = false)
    private ActiveStatus activeStatus;

    @Column(name = "anonymous_Status",nullable = false)
    private AnonymousStatus anonymousStatus;

    @Column(name = "user_id",nullable =false)
    private Long userId;

    @Column(name = "recruitment_board_comment_id",nullable =false)
    private Long recruitmentBoardCommentId;
}
