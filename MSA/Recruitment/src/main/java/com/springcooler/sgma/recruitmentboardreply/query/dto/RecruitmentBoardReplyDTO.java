package com.springcooler.sgma.recruitmentboardreply.query.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecruitmentBoardReplyDTO {

    private Long recruitmentBoardReplyId;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String anonymousStatus;

    private String activeStatus;

    private Long userId;

    private Long recruitmentBoardCommentId;

    private String userNickname;

}


