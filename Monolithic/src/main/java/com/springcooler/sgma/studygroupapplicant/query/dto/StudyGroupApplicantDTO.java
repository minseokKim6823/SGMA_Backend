package com.springcooler.sgma.studygroupapplicant.query.dto;

import lombok.Data;

@Data
public class StudyGroupApplicantDTO {
    private Long userId;
    private String applicationStatus;
    private Long recruitmentBoardId;
}
