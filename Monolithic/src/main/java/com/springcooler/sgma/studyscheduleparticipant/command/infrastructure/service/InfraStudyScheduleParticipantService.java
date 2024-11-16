package com.springcooler.sgma.studyscheduleparticipant.command.infrastructure.service;

public interface InfraStudyScheduleParticipantService {
    // 특정 참가자의 제출 문제 수를 증가시키는 메서드
    void increaseNumSubmittedProblems(Long scheduleId, Long participantId);

    // 특정 참가자의 제출 문제 수를 감소시키는 메서드
    void decreaseNumSubmittedProblems(Long scheduleId, Long participantId);

    // 특정 참가자의 채점 점수를 가져오는 메서드
    double gradeAndUpdateParticipantScore(long scheduleId, long participantId);
}