package com.springcooler.sgma.recruitmentboardreply.command.application.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.AnonymousStatus;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecruitmentBoardReplyDTO {

    private Long recruitmentBoardReplyId;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private AnonymousStatus anonymousStatus;

    private String activeStatus;

    private Long userId;

    private Long recruitmentBoardCommentId;
}


