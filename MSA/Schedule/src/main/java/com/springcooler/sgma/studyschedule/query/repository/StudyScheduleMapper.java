package com.springcooler.sgma.studyschedule.query.repository;

import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleParticipantVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyScheduleMapper {

    // 스터디 그룹 일정 단건 조회
    StudyScheduleDTO findStudyScheduleByScheduleId(Long scheduleId);

    // 스터디 그룹 일정 전체 조회
    List<StudyScheduleDTO> findStudyScheduleByGroupId(Long groupId);

    // 스터디 그룹 일정 기간별 조회
    List<StudyScheduleDTO> findStudySchedulesByPeriod(Long groupId, String startDate, String endDate);

    // 스터디 그룹 일정 시험의 통계 자료 조회
    StudyScheduleDTO findStudyScheduleByStatistics(Long scheduleId);

    List<Long> findParticipantsByScheduleId(Long scheduleId);
}
