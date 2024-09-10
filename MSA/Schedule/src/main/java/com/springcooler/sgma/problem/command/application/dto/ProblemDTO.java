package com.springcooler.sgma.problem.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProblemDTO {

    @JsonProperty("problem_id")
    private Long problemId;

    @JsonProperty("participant_id")
    private Long participantId;

    @JsonProperty("schedule_id")
    private Long scheduleId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("problem_type")
    private String problemType;

    @JsonProperty("choices")
    private List<String> choices;

}
