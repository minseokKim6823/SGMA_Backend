package com.springcooler.sgma.recruitmentboard.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecruitmentBoardCommandDTO {
    private Long recruitmentBoardId;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp recruitmentStartTime;
    private Timestamp recruitmentEndTime;
    private String activeStatus;
    private Integer likes;
    private Long groupId;
    private Integer studyGroupCategoryId;
}