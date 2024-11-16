package com.springcooler.sgma.recruitmentboardcomment.command.application.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentBoardCommentCommandDTO {

    private Long recruitmentBoardCommentId;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private long userId;

    private String activeStatus;

    private long recruitmentBoardId;

    private String anonymousStatus;
}
