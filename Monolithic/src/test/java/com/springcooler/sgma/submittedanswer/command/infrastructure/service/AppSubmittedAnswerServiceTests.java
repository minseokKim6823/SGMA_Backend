package com.springcooler.sgma.submittedanswer.command.infrastructure.service;

import com.springcooler.sgma.submittedanswer.command.application.dto.SubmittedAnswerDTO;
import com.springcooler.sgma.submittedanswer.command.application.service.AppSubmittedAnswerService;
import com.springcooler.sgma.submittedanswer.command.domain.aggregate.SubmittedAnswer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppSubmittedAnswerServiceTests {
    @Autowired
    private AppSubmittedAnswerService appSubmittedAnswerService;

    @DisplayName("답안 제출 테스트")
    @Test
    void testRegistAnswers(){

        // given
        List<SubmittedAnswerDTO> submittedAnswers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            SubmittedAnswerDTO answerDTO = new SubmittedAnswerDTO(i+1, 1, 1, "UNGRADED");
            submittedAnswers.add(answerDTO);
        }

        // when
        appSubmittedAnswerService.registSubmittedAnswer(submittedAnswers);

        // then


    }
    
    @DisplayName("제출 답안 수정 테스트")
    @Test
    @Order(2)
    void testModifySubmittedAnswer(){

        // given
        SubmittedAnswerDTO newSubmittedAnswerDTO = new SubmittedAnswerDTO(10,4,2, "UNGRADED");

        log.info("newSubmittedAnswerDTO: {}", newSubmittedAnswerDTO);

        // when
        SubmittedAnswer existingAnswer = appSubmittedAnswerService.findSubmittedAnswerByProblemIdAndParticipantId(newSubmittedAnswerDTO.getProblemId(), newSubmittedAnswerDTO.getParticipantId());
        log.info("existingAnswer: {}", existingAnswer);
        SubmittedAnswer modifySubmittedAnswer = appSubmittedAnswerService.modifySubmittedAnswer(newSubmittedAnswerDTO);

        // then
        assertNotNull(modifySubmittedAnswer);
        log.info("modifySubmittedAnswer: {}", modifySubmittedAnswer);
    }
//
//    @DisplayName("제출된 답안 채점 테스트")
//    @Test
//    @Order(3)
//    void testGradeSubmittedAnswers(){
//
//        // given
//        SubmittedAnswerDTO ungradedSubmittedAnswerDTO = new SubmittedAnswerDTO(1,9,1, "UNGRADED");
//        log.info("existingSubmittedAnswerDTO: {}", ungradedSubmittedAnswerDTO);
//
//        // when
//        SubmittedAnswer answerToGrade = appSubmittedAnswerService.findSubmittedAnswerByProblemIdAndParticipantId(ungradedSubmittedAnswerDTO.getProblemId(), ungradedSubmittedAnswerDTO.getParticipantId());
//        log.info("answerToGrade: {}", answerToGrade);
//
//        appSubmittedAnswerService.gradeSubmittedAnswer(ungradedSubmittedAnswerDTO);
//        SubmittedAnswer gradedAnswer = appSubmittedAnswerService.findSubmittedAnswerByProblemIdAndParticipantId(ungradedSubmittedAnswerDTO.getProblemId(), ungradedSubmittedAnswerDTO.getParticipantId());
//        log.info("gradedAnswer: {}", gradedAnswer);
//        // then
//        assertNotNull(gradedAnswer);
//    }

    @DisplayName("참여자 아이디로 채점 테스트")
    @Test
    void testGradeSubmittedAnswerByParticipantId(){

        // given
        long scheduleId = 1L;

        long participantId = 1L;

        // when
        appSubmittedAnswerService.gradeSubmittedAnswersByScheduleIdAndParticipantId(scheduleId, participantId);
    }

}