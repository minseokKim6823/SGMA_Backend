package com.springcooler.sgma.studyschedule.command.infrastructure.service;

import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import com.springcooler.sgma.studyschedule.command.domain.repository.StudyScheduleRepository;
import com.springcooler.sgma.studyschedule.common.exception.CommonException;
import com.springcooler.sgma.studyschedule.common.exception.ErrorCode;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.StudyScheduleParticipant;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.repository.StudyScheduleParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InfraStudyScheduleServiceImpl implements InfraStudyScheduleService {

    private final StudyScheduleParticipantRepository participantRepository;
    private final StudyScheduleRepository studyScheduleRepository;

    @Autowired
    public InfraStudyScheduleServiceImpl(StudyScheduleParticipantRepository participantRepository,
                                         StudyScheduleRepository studyScheduleRepository) {
        this.participantRepository = participantRepository;
        this.studyScheduleRepository = studyScheduleRepository;
    }

    // 일정에 따른 참가자들의 시험 평균 및 표준편차 계산 및 업데이트
    @Override
    public void updateScheduleWithParticipantScores(Long scheduleId) {
        List<StudyScheduleParticipant> participants = participantRepository.findByScheduleId(scheduleId);

        List<Integer> scores = participants.stream()
                .map(StudyScheduleParticipant::getTestScore)
                .toList();

        if (scores.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE);
        }

        // 평균과 표준편차 계산을 위한 초기값
        double sum = 0.0;
        double sumOfSquares = 0.0;

        // 점수 리스트를 순회하며 합계와 제곱합을 계산
        for (int score : scores) {
            sum += score;
            sumOfSquares += score * score;
        }

        // 평균 계산
        double average = sum / scores.size();

        // 분산 계산 (Var(X) = E(X^2) - (E(X))^2)
        double variance = (sumOfSquares / scores.size()) - (average * average);
        double standardDeviation = Math.sqrt(variance);

        // 소수점 둘째 자리까지 반올림
        average = Math.round(average * 100) / 100.0;
        standardDeviation = Math.round(standardDeviation * 100) / 100.0;

        StudySchedule schedule = studyScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE));

        schedule.setTestAverage(average);
        schedule.setTestStandardDeviation(standardDeviation);

        studyScheduleRepository.save(schedule);
    }
}

