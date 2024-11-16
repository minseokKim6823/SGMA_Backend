package com.springcooler.sgma.submittedanswer.command.domain.aggregate;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="SUBMITTED_ANSWER")
@IdClass(SubmittedAnswerPK.class)
public class SubmittedAnswer {

    @Id
    @Column(name="problem_id")
    private Long problemId;

    @Id
    @Column(name="participant_id")
    private Long participantId;

    @Column(name="submitted_answer")
    private int submittedAnswer;

    @Column(name="answer_status")
    private String answerStatus;

}
