package com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseStudyScheduleParticipantVO {

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
    private Integer testScore;

    @JsonProperty("test_percentage")
    private Double testPercentage;
}
