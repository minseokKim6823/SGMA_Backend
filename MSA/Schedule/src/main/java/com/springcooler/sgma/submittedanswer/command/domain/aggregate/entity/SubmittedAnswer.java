package com.springcooler.sgma.submittedanswer.command.domain.aggregate.entity;


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
    private String submittedAnswer;

    @Enumerated(EnumType.STRING)
    @Column(name="answer_status")
    private AnswerStatus answerStatus = AnswerStatus.UNGRADED;

    public SubmittedAnswer(Long problemId, Long participantId, String submittedAnswer) {
        this.problemId = problemId;
        this.participantId = participantId;
        this.submittedAnswer = submittedAnswer;
    }
}
