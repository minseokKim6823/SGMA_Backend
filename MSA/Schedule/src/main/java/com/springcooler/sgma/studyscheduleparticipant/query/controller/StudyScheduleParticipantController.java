package com.springcooler.sgma.studyscheduleparticipant.query.controller;

import com.springcooler.sgma.studyscheduleparticipant.common.ResponseDTO;
import com.springcooler.sgma.studyscheduleparticipant.query.dto.StudyScheduleParticipantDTO;
import com.springcooler.sgma.studyscheduleparticipant.query.service.StudyScheduleParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryStudyScheduleParticipantController")
@RequestMapping("/api/study-schedule")
public class StudyScheduleParticipantController {
    private final StudyScheduleParticipantService studyScheduleParticipantService;

    @Autowired
    public StudyScheduleParticipantController(StudyScheduleParticipantService studyScheduleParticipantService) {
        this.studyScheduleParticipantService = studyScheduleParticipantService;
    }

    // 스터디 그룹 일정 참가자 조회
    @GetMapping("/scheduleParticipant/{scheduleId}")
    public ResponseDTO<?> findScheduleParticipantByScheduleId(@PathVariable long scheduleId) {
        List<StudyScheduleParticipantDTO> participants = studyScheduleParticipantService.findStudyScheduleParticipant(scheduleId);
        return ResponseDTO.ok(participants);
    }

    // 스터디 그룹 일정 참가자의 시험결과 조회
    @GetMapping("/participantTestResult/{memberId}")
    public ResponseDTO<?> findParticipantTestResult(@PathVariable long memberId) {
        List<StudyScheduleParticipantDTO> participantTestResults = studyScheduleParticipantService.findStudyScheduleParticipantTestResult(memberId);
        return ResponseDTO.ok(participantTestResults);
    }

}