package com.springcooler.sgma.recruitmentboardreply.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentBoardReplyDTO {

    private Long recruitmentBoardReplyId;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String anonymousStatus;

    private Long userId;

    private Long recruitmentBoardCommentId;
}


