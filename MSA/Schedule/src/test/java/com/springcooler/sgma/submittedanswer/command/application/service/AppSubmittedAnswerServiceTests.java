package com.springcooler.sgma.submittedanswer.command.application.service;

import com.springcooler.sgma.submittedanswer.command.application.dto.SubmittedAnswerDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Transactional
class AppSubmittedAnswerServiceTests {

    @Autowired
    private AppSubmittedAnswerService appSubmittedAnswerService;
    
    @DisplayName("객관식 문제에 대한 제출 답안 등록 테스트")
    @Test
    void testRegistMultipleChoiceProblemAnswer(){

        // given
        Long problemId =4L;
        Long participantId = 1L;
        String submittedAnswer = "1";

        // when
        SubmittedAnswerDTO submittedAnswerInfo = new SubmittedAnswerDTO(problemId, participantId, submittedAnswer);
        List<SubmittedAnswerDTO> submittedAnswers = new ArrayList<>();
        submittedAnswers.add(submittedAnswerInfo);

        // then
        assertDoesNotThrow(()->appSubmittedAnswerService.registSubmittedAnswer(submittedAnswers));
    }

    @DisplayName("주관식 문제에 대한 답안 제출 테스트")
    @Test
    void testRegistEssayProblemAnswer(){

        // given
        Long problemId = 13L;
        Long participantId = 6L;
        String submittedAnswer = "주관식 답안 제출 테스트";

        // when
        List<SubmittedAnswerDTO> submittedAnswers = new ArrayList<>();
        SubmittedAnswerDTO submittedAnswerInfo = new SubmittedAnswerDTO(problemId, participantId, submittedAnswer);
        submittedAnswers.add(submittedAnswerInfo);
        // then
        assertDoesNotThrow(()-> appSubmittedAnswerService.registSubmittedAnswer(submittedAnswers));
    }

    @DisplayName("참여자 ID로 제출답안 채점 테스트")
    @Test
    void testGradeSubmittedAnswerByParticipantId(){

        // given
        Long scheduleId = 1L;
        Long participantId = 1L;

        // when

        // then
        appSubmittedAnswerService.gradeSubmittedAnswersByScheduleIdAndParticipantId(scheduleId, participantId);

    }
}