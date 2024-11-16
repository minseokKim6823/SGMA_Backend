package com.springcooler.sgma.studyschedule.query.service;

import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleDTO;

import java.util.List;

public interface StudyScheduleService {

    // 스터디 그룹 일정 단건 조회
    StudyScheduleDTO findStudyScheduleByScheduleId(long scheduleId);

    // 스터디 그룹 일정 전체 조회
    List<StudyScheduleDTO> findStudyScheduleByGroupId(long groupId);

    // 스터디 그룹 일정 기간별 조회
    List<StudyScheduleDTO> findStudySchedulesByPeriod(long groupId, String startDate, String endDate);

    // 스터디 그룹 일정 시험의 통계 자료 조회 (scheduleID로 조회)
    StudyScheduleDTO findStudyScheduleByStatistics(long scheduleID);
}