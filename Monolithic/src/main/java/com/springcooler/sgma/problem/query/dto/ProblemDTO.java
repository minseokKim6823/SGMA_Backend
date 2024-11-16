package com.springcooler.sgma.problem.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProblemDTO {

    @JsonProperty("problem_id")
    private long problemId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("answer")
    private int answer;

    @JsonProperty("participant_id")
    private long participantId;

    @JsonProperty("schedule_id")
    private long scheduleId;

}
