package com.springcooler.sgma.studyscheduleparticipant.query.service;

import com.springcooler.sgma.studyscheduleparticipant.common.exception.CommonException;
import com.springcooler.sgma.studyscheduleparticipant.common.exception.ErrorCode;
import com.springcooler.sgma.studyscheduleparticipant.query.dto.StudyScheduleParticipantDTO;
import com.springcooler.sgma.studyscheduleparticipant.query.repository.StudyScheduleParticipantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyScheduleParticipantServiceImpl implements StudyScheduleParticipantService
{
    private final StudyScheduleParticipantMapper studyScheduleParticipantMapper;

    @Autowired
    public StudyScheduleParticipantServiceImpl(StudyScheduleParticipantMapper studyScheduleParticipantMapper) {
        this.studyScheduleParticipantMapper = studyScheduleParticipantMapper;
    }

    // 스터디 그룹 일정 참가자 조회
    @Override
    public List<StudyScheduleParticipantDTO> findStudyScheduleParticipant(long scheduleId) {
        List<StudyScheduleParticipantDTO> participants = studyScheduleParticipantMapper.findStudyScheduleParticipant(scheduleId);
        if (participants == null || participants.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT);
        }
        return participants;
    }

    // 스터디 그룹 일정 참가자의 시험 조회
    @Override
    public List<StudyScheduleParticipantDTO> findStudyScheduleParticipantTestResult(long memberId) {
        List<StudyScheduleParticipantDTO> participantTestResults = studyScheduleParticipantMapper.findStudyScheduleParticipantTestResult(memberId);
        if (participantTestResults == null || participantTestResults.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT);
        }
        return participantTestResults;
    }

}
