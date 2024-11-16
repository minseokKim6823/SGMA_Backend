package com.springcooler.sgma.recruitmentboardreply.query.dto;


import lombok.Data;

import java.sql.Timestamp;


@Data
public class RecruitmentBoardReplyDTO {

    private Long recruitmentBoardReplyId;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String anonymousStatus;

    private String activeStatus;

    private Long userId;

    private Long recruitmentBoardCommentId;
}


