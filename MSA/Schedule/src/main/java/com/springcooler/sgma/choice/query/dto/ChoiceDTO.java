package com.springcooler.sgma.choice.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChoiceDTO {

    @JsonProperty("problem_id")
    private long problemId;

    @JsonProperty("choice_num")
    private long choiceNum;

    @JsonProperty("content")
    private String content;
}
