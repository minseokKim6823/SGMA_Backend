package com.springcooler.sgma.problem.command.domain.aggregate.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="PROBLEM")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Problem {

    @Id
    @Column(name="problem_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problemId;

    @Column(name="content")
    private String content;

    @Column(name="answer")
    private int answer;

    @Column(name="participant_id")
    private long participantId;

    @Column(name="schedule_id")
    private long scheduleId;

}
