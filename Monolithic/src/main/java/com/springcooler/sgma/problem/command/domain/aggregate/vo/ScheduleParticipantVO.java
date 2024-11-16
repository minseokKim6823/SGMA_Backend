package com.springcooler.sgma.problem.command.domain.aggregate.vo;

import lombok.*;

import java.util.Map;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleParticipantVO {
    private long scheduleId;
    private Map<Long, Long> participants;
}
