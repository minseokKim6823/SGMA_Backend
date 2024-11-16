package com.springcooler.sgma.submittedanswer.command.application.controller;

import com.springcooler.sgma.submittedanswer.command.application.dto.SubmittedAnswerDTO;
import com.springcooler.sgma.submittedanswer.command.application.service.AppSubmittedAnswerService;
import com.springcooler.sgma.submittedanswer.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("commandSubmittedAnswerController")
@RequestMapping("/api/submitted-answers")
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
    public ResponseDTO<?> gradeSubmittedAnswerByParticipantId(@PathVariable("scheduleId") long scheduleId, @PathVariable("participantId") long participantId){
        return ResponseDTO.ok(String.valueOf(appSubmittedAnswerService.gradeSubmittedAnswersByScheduleIdAndParticipantId(scheduleId, participantId)));
    }

    }




