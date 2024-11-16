package com.springcooler.sgma.choice.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ChoicePK implements Serializable {

    @Column(name="problem_id")
    private Long problemId;

    @Column(name="choice_num")
    private Integer choiceNum;
}
