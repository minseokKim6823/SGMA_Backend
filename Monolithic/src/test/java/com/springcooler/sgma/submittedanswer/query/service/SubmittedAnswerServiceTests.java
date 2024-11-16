package com.springcooler.sgma.submittedanswer.query.service;

import com.springcooler.sgma.submittedanswer.query.dto.SubmittedAnswerDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SubmittedAnswerServiceTests {
    @Autowired
    private SubmittedAnswerService submittedAnswerService;

    @DisplayName("제출 답안 전체 조회 테스트")
    @Test
    void testFindAllSubmittedAnswers(){

        // given

        // when
        List<SubmittedAnswerDTO> submittedAnswers = submittedAnswerService.findAllSubmittedAnswers();
        // then
        assertNotNull(submittedAnswers);
        log.info("submittedAnswers.toString(): {}", submittedAnswers);
        submittedAnswers.forEach(x-> log.info("x: {}", x));
    }

    @DisplayName("문제 ID로 제출 답안 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindSubmittedAnswerByProblemId(long problemId){

        //given
        List<SubmittedAnswerDTO> submittedAnswers = submittedAnswerService.findSubmittedAnswersByProblemId(problemId);

        // when

        // then
        assertNotNull(submittedAnswers);
        submittedAnswers.forEach(x-> log.info("x: {}", x));
    }



}