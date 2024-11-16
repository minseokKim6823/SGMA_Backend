package com.springcooler.sgma.studyschedule.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="STUDY_SCHEDULE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SCHEDULE_ID")
    private Long scheduleId;

    @Column(name="TITLE", columnDefinition = "TEXT")
    private String title;

    @Column(name="CONTENT", columnDefinition = "TEXT")
    private String content;

    @Column(name = "SCHEDULE_START_TIME", columnDefinition = "TIMESTAMP")
    private java.sql.Timestamp scheduleStartTime;

    @Column(name = "SCHEDULE_END_TIME", columnDefinition = "TIMESTAMP")
    private java.sql.Timestamp scheduleEndTime;

    @Column(name = "NUM_PARTICIPANTS")
    private Integer numParticipants;

    @Enumerated(EnumType.STRING)
    @Column(name="ACTIVE_STATUS")
    private StudyScheduleStatus activeStatus;

    @Column(name="TEST_STATUS", columnDefinition = "TEXT")
    private String testStatus;

    @Column(name="TEST_AVERAGE")
    private Double testAverage;

    @Column(name="TEST_STANDARD_DEVIATION")
    private Double testStandardDeviation;

    @Column(name="GROUP_ID")
    private Long groupId;

    @Column(name="NUM_PROBLEMS_PER_PARTICIPANT")
    private Integer numProblemsPerParticipant;
}