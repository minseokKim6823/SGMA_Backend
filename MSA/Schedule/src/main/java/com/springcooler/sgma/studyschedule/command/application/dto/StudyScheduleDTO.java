package com.springcooler.sgma.studyschedule.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudyScheduleStatus;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyScheduleDTO {
    private Long scheduleId;
    private String title;
    private String content;
    private Timestamp scheduleStartTime;
    private Timestamp scheduleEndTime;
    private Integer numParticipants;
    private StudyScheduleStatus activeStatus;
    private String testStatus;
    private Double testAverage;
    private Double testStandardDeviation;
    private Long groupId;
    private Integer numProblemsPerParticipant;
}
