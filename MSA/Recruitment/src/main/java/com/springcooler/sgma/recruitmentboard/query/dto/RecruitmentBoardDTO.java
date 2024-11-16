package com.springcooler.sgma.recruitmentboard.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecruitmentBoardDTO {

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

    private String studyGroupCategoryName;

    private Long userId;

    private String userNickname;

}