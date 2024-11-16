package com.springcooler.sgma.submittedanswer.command.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class SubmittedAnswerPK implements Serializable {
    @Column(name = "problem_id")
    private Long problemId;

    @Column(name="participant_id")
    private Long participantId;
}
