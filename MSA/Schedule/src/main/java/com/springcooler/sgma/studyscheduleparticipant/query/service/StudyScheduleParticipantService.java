package com.springcooler.sgma.studyscheduleparticipant.query.service;

import com.springcooler.sgma.studyscheduleparticipant.query.dto.StudyScheduleParticipantDTO;

import java.util.List;

public interface StudyScheduleParticipantService{

    // 스터디 그룹 일정 참가자 조회
    List<StudyScheduleParticipantDTO> findStudyScheduleParticipant(long scheduleId);

    // 스터디 그룹 일정 참가자의 시험 결과 조회
    List<StudyScheduleParticipantDTO> findStudyScheduleParticipantTestResult(long memberId);


}
