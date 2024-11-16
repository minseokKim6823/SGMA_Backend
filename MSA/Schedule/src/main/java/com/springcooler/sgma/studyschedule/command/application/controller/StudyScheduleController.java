package com.springcooler.sgma.studyschedule.command.application.controller;

import com.springcooler.sgma.studyschedule.command.application.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.command.application.service.AppStudyScheduleService;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.vo.RequestStudyScheduleVO;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.vo.ResponseStudyScheduleVO;
import com.springcooler.sgma.studyschedule.common.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController("commandStudyScheduleController")
@RequestMapping("/api/study-schedule")
public class StudyScheduleController {

    private final ModelMapper modelMapper;
    private final AppStudyScheduleService studyScheduleService;

    @Autowired
    public StudyScheduleController(ModelMapper modelMapper,
                                   AppStudyScheduleService studyScheduleService) {
        this.modelMapper = modelMapper;
        this.studyScheduleService = studyScheduleService;
    }

    // 스터디 일정 생성
    @PostMapping("/")
    public ResponseDTO<?> registStudySchedule(@RequestBody RequestStudyScheduleVO newStudySchedule) {
        StudyScheduleDTO studySchedule = modelMapper.map(newStudySchedule, StudyScheduleDTO.class);
        studySchedule = studyScheduleService.registStudySchedule(studySchedule);
        ResponseStudyScheduleVO res = modelMapper.map(studySchedule, ResponseStudyScheduleVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디 일정 수정
    @PutMapping("/")
    public ResponseDTO<?> modifyStudySchedule(@RequestBody RequestStudyScheduleVO modifyStudySchedule) {
        StudyScheduleDTO studySchedule = modelMapper.map(modifyStudySchedule, StudyScheduleDTO.class);
        studySchedule = studyScheduleService.modifyStudySchedule(studySchedule);
        ResponseStudyScheduleVO res = modelMapper.map(studySchedule, ResponseStudyScheduleVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseDTO<?> deleteStudySchedule(@PathVariable Long scheduleId) {
        studyScheduleService.deleteStudySchedule(scheduleId);
        return ResponseDTO.ok(null);
    }

//    // 시험 평균과 표준편차 업데이트
//    @PutMapping("/updateScores/{scheduleId}")
//    public ResponseDTO<?> updateScheduleScores(@PathVariable Long scheduleId) {
//        StudySchedule score = studyScheduleService.updateScheduleWithParticipantScores(scheduleId);
//        return ResponseDTO.ok(score);
//    }
}