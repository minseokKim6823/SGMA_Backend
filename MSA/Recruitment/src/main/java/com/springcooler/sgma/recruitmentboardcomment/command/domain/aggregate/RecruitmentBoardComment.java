package com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RecruitmentBoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recruitment_board_comment_id")
    private Long recruitmentBoardCommentId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at",columnDefinition = "TIMESTAMP",nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at",columnDefinition = "TIMESTAMP",nullable = false)
    private Timestamp updatedAt;

    @Column(name="user_id",nullable = false)
    private long userId;

    @Column(name="active_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @Column(name = "recruitment_board_id",nullable = false)
    private long recruitmentBoardId;

    @Column(name ="anonymous_status" ,nullable = false)
    @Enumerated(EnumType.STRING)
    private AnonymousStatus anonymousStatus;
}
