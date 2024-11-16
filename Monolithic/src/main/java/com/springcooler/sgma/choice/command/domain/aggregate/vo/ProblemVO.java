package com.springcooler.sgma.choice.command.domain.aggregate.vo;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProblemVO {
    private long problemId;
    private List<String> choices;
}
