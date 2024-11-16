package com.springcooler.sgma.studyscheduleparticipant.command.domain.repository;

import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.StudyScheduleParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyScheduleParticipantRepository extends JpaRepository<StudyScheduleParticipant, Long> {

    // 특정 일정 ID에 해당하는 모든 참가자 조회 메서드 추가
    List<StudyScheduleParticipant> findByScheduleId(Long scheduleId);

    Optional<StudyScheduleParticipant> findByScheduleIdAndMemberId(Long scheduleId, Long memberId);

    Optional<StudyScheduleParticipant> findByScheduleIdAndParticipantId(Long scheduleId, Long participantId);

    List<StudyScheduleParticipant> findByScheduleIdAndSubmissionStatus(Long scheduleId, String y);
}