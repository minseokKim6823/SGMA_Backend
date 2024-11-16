package com.springcooler.sgma.studygroupapplicant.command.application.dto;

import lombok.Data;

@Data
public class StudyGroupApplicantCommandDTO {
    private Long userId;
    private String applicationStatus;
    private Long recruitmentBoardId;
    private Long groupId;
}
