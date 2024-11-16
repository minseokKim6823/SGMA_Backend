package com.springcooler.sgma.choice.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProblemVO {

    @JsonProperty("problem_id")
    private long problemId;

    @JsonProperty("choices")
    private List<String> choices;
}
