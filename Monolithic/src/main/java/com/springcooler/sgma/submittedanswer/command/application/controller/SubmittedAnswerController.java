package com.springcooler.sgma.submittedanswer.command.application.controller;

import com.springcooler.sgma.submittedanswer.command.application.dto.SubmittedAnswerDTO;
import com.springcooler.sgma.submittedanswer.command.application.service.AppSubmittedAnswerService;
import com.springcooler.sgma.submittedanswer.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("commandSubmittedAnswerController")
@RequestMapping("/api/submitted-answers/")
public class SubmittedAnswerController {

    AppSubmittedAnswerService appSubmittedAnswerService;

    @Autowired
    public SubmittedAnswerController(AppSubmittedAnswerService appSubmittedAnswerService) {
        this.appSubmittedAnswerService = appSubmittedAnswerService;
    }

    @PostMapping("/")
    public ResponseDTO<?> registSubmittedAnswer(@RequestBody List<SubmittedAnswerDTO> newSubmittedAnswer){
        appSubmittedAnswerService.registSubmittedAnswer(newSubmittedAnswer);
        return ResponseDTO.ok(null);
    }

    @PutMapping("/")
    public ResponseDTO<?> modifySubmittedAnswer(@RequestBody SubmittedAnswerDTO modifySubmittedAnswer){
        return ResponseDTO.ok(appSubmittedAnswerService.modifySubmittedAnswer(modifySubmittedAnswer));
    }


    @PutMapping("/grade/{scheduleId}/{participantId}")
    public ResponseDTO<?> gradeSubmittedAnswerByParticipantId(@PathVariable long scheduleId, @PathVariable long participantId){
        return ResponseDTO.ok(String.valueOf(appSubmittedAnswerService.gradeSubmittedAnswersByScheduleIdAndParticipantId(scheduleId, participantId)));
    }

    }
//    @PutMapping("/grade/")// TODO: 시험 종료시 해당 시험에 해당하는 문제 한번에 채점
//    public ResponseEntity<?> gradeSubmittedAnswer(@RequestBody SubmittedAnswerDTO answerToGrade){
//
//        return ResponseEntity.created(URI.create("/api/submitted-answers/grade/" + appSubmittedAnswerService.gradeSubmittedAnswer(answerToGrade).getAnswerStatus())).build();
//    }



