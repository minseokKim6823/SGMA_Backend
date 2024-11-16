package com.springcooler.sgma.studyschedule.query.service;

import com.springcooler.sgma.studyschedule.common.exception.CommonException;
import com.springcooler.sgma.studyschedule.common.exception.ErrorCode;
import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleParticipantVO;
import com.springcooler.sgma.studyschedule.query.repository.StudyScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyScheduleServiceImpl implements StudyScheduleService {
    private final StudyScheduleMapper studyScheduleMapper;

    @Autowired
    public StudyScheduleServiceImpl(StudyScheduleMapper studyScheduleMapper) {
        this.studyScheduleMapper = studyScheduleMapper;
    }

    // 스터디 그룹 일정 단건 조회
    @Override
    public StudyScheduleDTO findStudyScheduleByScheduleId(long scheduleId) {
        StudyScheduleDTO schedule = studyScheduleMapper.findStudyScheduleByScheduleId(scheduleId);
        if (schedule == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE);
        }
        return schedule;
    }

    // 스터디 그룹 일정 전체 조회
    @Override
    public List<StudyScheduleDTO> findStudyScheduleByGroupId(long groupId) {
        List<StudyScheduleDTO> schedules = studyScheduleMapper.findStudyScheduleByGroupId(groupId);
        if (schedules == null || schedules.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE);
        }
        return schedules;
    }

    // 스터디 그룹 일정 기간별 조회
    @Override
    public List<StudyScheduleDTO> findStudySchedulesByPeriod(long groupId, String startDate, String endDate) {
        return studyScheduleMapper.findStudySchedulesByPeriod(groupId, startDate, endDate);
    }

    // 스터디 그룹 일정 시험의 통계 자료 조회
    @Override
    public StudyScheduleDTO findStudyScheduleByStatistics(long scheduleId) {
        StudyScheduleDTO statistics = studyScheduleMapper.findStudyScheduleByStatistics(scheduleId);
        if (statistics == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE);
        }
        return statistics;
    }

    @Override
    public StudyScheduleParticipantVO findParticipantsByScheduleId(long scheduleId) {
        List<Long> participants = studyScheduleMapper.findParticipantsByScheduleId(scheduleId);
        if (participants == null || participants.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE);
        }
        StudyScheduleParticipantVO scheduleVO = new StudyScheduleParticipantVO(scheduleId, participants);
        return scheduleVO;
    }
}
