package com.springcooler.sgma.submittedanswer.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SubmittedAnswerDTO {

    @JsonProperty("problem_id")
    private long problemId;

    @JsonProperty("participant_id")
    private long participantId;

    @JsonProperty("submitted_answer")
    private int submittedAnswer;

    @JsonProperty("answer_status")
    private String answerStatus;

}
