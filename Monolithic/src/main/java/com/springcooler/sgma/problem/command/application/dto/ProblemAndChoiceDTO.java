package com.springcooler.sgma.problem.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProblemAndChoiceDTO {

    @JsonProperty("problem_id")
    private Long problemId;

    @JsonProperty("participant_id")
    private long participantId;

    @JsonProperty("schedule_id")
    private long scheduleId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("answer")
    private int answer;

    @JsonProperty("choices")
    private List<String> choices;

}
