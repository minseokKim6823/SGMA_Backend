package com.springcooler.sgma.studyscheduleparticipant.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyScheduleParticipantDTO {

    private Long participantId;
    private Long scheduleId;
    private Long memberId;
    private String submissionStatus;
    private Integer numSubmittedProblems;
    private Integer testScore;
    private Double testPercentage;
}
