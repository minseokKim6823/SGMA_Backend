package com.springcooler.sgma.studyscheduleparticipant.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyScheduleParticipantDTO {

    @JsonProperty("participant_id")
    private Long participantId;

    @JsonProperty("schedule_id")
    private Long scheduleId;

    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("submission_status")
    private String submissionStatus;

    @JsonProperty("num_submitted_problems")
    private Integer numSubmittedProblems;

    @JsonProperty("test_score")
    private Double testScore;

    @JsonProperty("test_percentage")
    private Double testPercentage;
}
