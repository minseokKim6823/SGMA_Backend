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
    private Long problemId;

    @JsonProperty("participant_id")
    private Long participantId;

    @JsonProperty("submitted_answer")
    private String submittedAnswer;

}
