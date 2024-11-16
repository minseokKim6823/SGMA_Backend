package com.springcooler.sgma.studyschedule.query.repository;

import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyScheduleMapper {

    // 스터디 그룹 일정 단건 조회
    StudyScheduleDTO findStudyScheduleByScheduleId(long scheduleId);

    // 스터디 그룹 일정 전체 조회
    List<StudyScheduleDTO> findStudyScheduleByGroupId(long groupId);

    // 스터디 그룹 일정 기간별 조회
    List<StudyScheduleDTO> findStudySchedulesByPeriod(long groupId, String startDate, String endDate);

    // 스터디 그룹 일정 시험의 통계 자료 조회
    StudyScheduleDTO findStudyScheduleByStatistics(long scheduleId);

}
