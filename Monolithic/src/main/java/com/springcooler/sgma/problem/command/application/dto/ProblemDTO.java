package com.springcooler.sgma.problem.command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProblemDTO {
    private long problemId;
    private String content;
    private int answer;
    private long participantId;
    private long scheduleId;

}
