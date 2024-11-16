package com.springcooler.sgma.studyschedule.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudyScheduleStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseStudyScheduleVO {

    @JsonProperty("schedule_id")
    private Long scheduleId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("schedule_start_time")
    private java.sql.Timestamp scheduleStartTime;

    @JsonProperty("schedule_end_time")
    private java.sql.Timestamp scheduleEndTime;

    @JsonProperty("num_participants")
    private Integer numParticipants;

    @Enumerated(EnumType.STRING)
    @JsonProperty("active_status")
    private StudyScheduleStatus activeStatus;

    @JsonProperty("test_status")
    private String testStatus;

    @JsonProperty("test_average")
    private Double testAverage;

    @JsonProperty("test_standard_deviation")
    private Double testStandardDeviation;

    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("num_problems_per_participant")
    private Integer numProblemsPerParticipant;
}
