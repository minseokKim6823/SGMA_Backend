package com.springcooler.sgma.studygroupapplicant.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyGroupApplicantDTO {

    private Long userId;

    private String applicationStatus;

    private Long recruitmentBoardId;

    private String nickname;
    
}
