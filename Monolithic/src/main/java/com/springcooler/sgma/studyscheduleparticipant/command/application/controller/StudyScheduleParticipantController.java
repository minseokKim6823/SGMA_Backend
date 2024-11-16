package com.springcooler.sgma.studyscheduleparticipant.command.application.controller;

import com.springcooler.sgma.studyscheduleparticipant.command.application.service.AppStudyScheduleParticipantService;
import com.springcooler.sgma.studyscheduleparticipant.command.application.dto.StudyScheduleParticipantDTO;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.StudyScheduleParticipant;
import com.springcooler.sgma.studyscheduleparticipant.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/study-schedule")
public class StudyScheduleParticipantController {

    private final AppStudyScheduleParticipantService studyScheduleParticipantService;

    @Autowired
    public StudyScheduleParticipantController(AppStudyScheduleParticipantService studyScheduleParticipantService) {
        this.studyScheduleParticipantService = studyScheduleParticipantService;
    }

    // 스터디 일정 참가자 등록
    @PostMapping("/participant")
    public ResponseDTO<?> registerParticipant(@RequestBody StudyScheduleParticipantDTO newParticipant) {
        StudyScheduleParticipant participant = studyScheduleParticipantService.registStudyScheduleParticipant(newParticipant);
        return ResponseDTO.ok(participant);
    }

    // 스터디 일정 참가자 삭제
    @DeleteMapping("/participant/{scheduleId}/{memberId}")
    public ResponseDTO<?> deleteParticipant(@PathVariable Long scheduleId, @PathVariable Long memberId) {
        studyScheduleParticipantService.deleteStudyScheduleParticipant(scheduleId, memberId);
        return ResponseDTO.ok(null);
    }
}
