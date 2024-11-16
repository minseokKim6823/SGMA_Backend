package com.springcooler.sgma.choice.command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChoiceDTO {
    private Long problemId;
    private Integer choiceNum;
    private String content;
}
