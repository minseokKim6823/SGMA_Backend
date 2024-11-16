package com.springcooler.sgma.studygroupapplicant.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyGroupApplicantCommandDTO {
    private Long userId;
    private String applicationStatus;
    private Long recruitmentBoardId;
    private Long groupId;
}
