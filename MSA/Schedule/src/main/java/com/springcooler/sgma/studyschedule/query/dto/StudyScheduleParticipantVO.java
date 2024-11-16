package com.springcooler.sgma.studyschedule.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StudyScheduleParticipantVO {
    private Long scheduleId;
    private List<Long> participants;
}
