package com.springcooler.sgma.studyscheduleparticipant.query.repository;

import com.springcooler.sgma.studyscheduleparticipant.query.dto.StudyScheduleParticipantDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyScheduleParticipantMapper {

    // 스터디 그룹 일정 참가자 조회
    List<StudyScheduleParticipantDTO> findStudyScheduleParticipant(long scheduleId);

    // 스터디 그룹 일정 참가자의 시험 결과 조회
    List<StudyScheduleParticipantDTO> findStudyScheduleParticipantTestResult(long scheduleId);
}