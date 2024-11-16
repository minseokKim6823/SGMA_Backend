package com.springcooler.sgma.studyschedule.command.infrastructure.service;

public interface InfraStudyScheduleService {

    // 일정에 따른 참가자들의 시험 평균 및 표준편차 계산 및 업데이트
    void updateScheduleWithParticipantScores(Long scheduleId);
}
