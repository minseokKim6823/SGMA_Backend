package com.springcooler.sgma.recruitmentboardcomment.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecruitmentBoardCommentCommandDTO {

    private Long recruitmentBoardCommentId;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Long userId;

    private String activeStatus;

    private Long recruitmentBoardId;

    private String anonymousStatus;
}
