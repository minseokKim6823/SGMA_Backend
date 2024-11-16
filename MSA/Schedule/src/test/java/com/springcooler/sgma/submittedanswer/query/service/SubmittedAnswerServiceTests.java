package com.springcooler.sgma.submittedanswer.query.service;

import com.springcooler.sgma.submittedanswer.query.dto.SubmittedAnswerDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SubmittedAnswerServiceTests {

    @Autowired
    SubmittedAnswerService submittedAnswerService;

    @DisplayName("제출답안 전체 조회 테스트")
    @Test
    void testFindAllSubmittedAnswers(){

        // given

        // when
        List<SubmittedAnswerDTO> submittedAnswers = submittedAnswerService.findAllSubmittedAnswers();

        // then
        assertNotNull(submittedAnswers);
        submittedAnswers.forEach(x->log.info(x.toString()));
    }

    @DisplayName("문제 ID로 제출 답안 조회")
    @Test
    void testFindSubmittedAnswersByProblemId(){

        // given
        Long problemId = 1L;

        // when
        List<SubmittedAnswerDTO> submittedAnswers = submittedAnswerService.findSubmittedAnswersByProblemId(problemId);

        // then
        assertNotNull(submittedAnswers);
        submittedAnswers.forEach(x->log.info(x.toString()));
    }

    @DisplayName("참가자 ID로 제출 답안 조회")
    @Test
    void testFindSubmittedAnswersByParticipantId(){

        // given
        Long participantId = 1L;

        // when
        List<SubmittedAnswerDTO> submittedAnswers = submittedAnswerService.findSubmittedAnswersByParticipantId(participantId);

        // then
        assertNotNull(submittedAnswers);
        submittedAnswers.forEach(x->log.info(x.toString()));
    }
}