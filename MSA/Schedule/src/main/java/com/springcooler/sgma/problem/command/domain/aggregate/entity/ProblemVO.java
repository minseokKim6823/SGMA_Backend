package com.springcooler.sgma.problem.command.domain.aggregate.entity;

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
