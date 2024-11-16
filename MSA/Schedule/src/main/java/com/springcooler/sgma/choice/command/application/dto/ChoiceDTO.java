package com.springcooler.sgma.choice.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChoiceDTO {

    @JsonProperty("problem_id")
    private Long problemId;

    @JsonProperty("choice_num")
    private Integer choiceNum;

    @JsonProperty("content")
    private String content;
}
