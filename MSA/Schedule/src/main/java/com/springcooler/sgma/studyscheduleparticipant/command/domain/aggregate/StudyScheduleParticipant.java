package com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "STUDY_SCHEDULE_PARTICIPANT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyScheduleParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARTICIPANT_ID")
    private Long participantId;

    @Column(name = "SCHEDULE_ID")
    private Long scheduleId;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "SUBMISSION_STATUS")
    private String submissionStatus;

    @Column(name = "NUM_SUBMITTED_PROBLEMS")
    private Integer numSubmittedProblems;

    @Column(name = "TEST_SCORE")
    private Integer testScore;

    @Column(name = "TEST_PERCENTAGE")
    private Double testPercentage;
}