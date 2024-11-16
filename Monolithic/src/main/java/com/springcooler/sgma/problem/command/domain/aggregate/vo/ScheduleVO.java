package com.springcooler.sgma.problem.command.domain.aggregate.vo;

import lombok.*;

import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleVO {
    private long scheduleId;
    private Timestamp scheduleStartTime;
    private Timestamp scheduleEndTime;
}
