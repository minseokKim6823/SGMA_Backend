package com.springcooler.sgma.studyschedule.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyScheduleDTO {

    @JsonProperty("schedule_id")
    private Long scheduleId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("schedule_start_time")
    private Timestamp scheduleStartTime;

    @JsonProperty("schedule_end_time")
    private Timestamp scheduleEndTime;

    @JsonProperty("num_participants")
    private Integer numParticipants;

    @JsonProperty("active_status")
    private String activeStatus;

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
