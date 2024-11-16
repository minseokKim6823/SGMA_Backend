package com.springcooler.sgma.studyschedule.command.application.controller;

import com.springcooler.sgma.studyschedule.command.application.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.command.application.service.AppStudyScheduleService;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import com.springcooler.sgma.studyschedule.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/study-schedule")
public class StudyScheduleController {

    private final AppStudyScheduleService studyScheduleService;

    @Autowired
    public StudyScheduleController(AppStudyScheduleService studyScheduleService) {
        this.studyScheduleService = studyScheduleService;
    }

    // 스터디 일정 생성
    @PostMapping("/")
    public ResponseDTO<?> registStudySchedule(@RequestBody StudyScheduleDTO newStudySchedule) {
        StudySchedule schedule = studyScheduleService.registStudySchedule(newStudySchedule);
        return ResponseDTO.ok(schedule);
    }

    // 스터디 일정 수정
    @PutMapping("/")
    public ResponseDTO<?> modifyStudySchedule(@RequestBody StudyScheduleDTO updateStudySchedule) {
        StudySchedule schedule = studyScheduleService.modifyStudySchedule(updateStudySchedule);
        return ResponseDTO.ok(schedule);
    }

    // 스터디 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseDTO<?> deleteStudySchedule(@PathVariable Long scheduleId) {
        studyScheduleService.deleteStudySchedule(scheduleId);
        return ResponseDTO.ok(null);
    }

    // 시험 평균과 표준편차 업데이트
    @PutMapping("/updateScores/{scheduleId}")
    public ResponseDTO<?> updateScheduleScores(@PathVariable Long scheduleId) {
        StudySchedule score = studyScheduleService.updateScheduleWithParticipantScores(scheduleId);
        return ResponseDTO.ok(score);
    }
}
