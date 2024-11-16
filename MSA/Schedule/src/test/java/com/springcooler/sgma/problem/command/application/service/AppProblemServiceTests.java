package com.springcooler.sgma.problem.command.application.service;

import com.springcooler.sgma.problem.command.application.dto.ProblemDTO;
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
class AppProblemServiceTests {

    @Autowired
    private AppProblemService appProblemService;

    @DisplayName("객관식 문제 등록 테스트")
    @Test
    void testRegistMultipleChoiceProblem() {

        // given
        List<String> choices = new ArrayList<>();
        choices.add("객관식 문제 등록 테스트 선지 1");
        choices.add("객관식 문제 등록 테스트 선지 2");
        choices.add("객관식 문제 등록 테스트 선지 3");
        choices.add("객관식 문제 등록 테스트 선지 4");
        ProblemDTO newMultipleChoiceProblem = new ProblemDTO(null, 5L, 2L, "객관식문제등록테스트", "4", "MULTIPLE", choices);

        // when
        ProblemDTO testProblemDTO = appProblemService.registProblem(newMultipleChoiceProblem);

        // then
        assertNotNull(testProblemDTO);
        log.info(testProblemDTO.toString());
    }

    @DisplayName("주관식 문제 등록 테스트")
    @Test
    void testRegistEssayProblemTest(){
        // given
        List<String> choices = new ArrayList<>();
        choices.add("주관식 문제 등록 테스트 선지 1");
        choices.add("주관식 문제 등록 테스트 선지 2");
        choices.add("주관식 문제 등록 테스트 선지 3");
        choices.add("주관식 문제 등록 테스트 선지 4");
        ProblemDTO newEssayProblem = new ProblemDTO(null, 5L, 2L, "주관식문제등록테스트", "주관식문제정답", "ESSAY",null);

        // when
        ProblemDTO testProblemDTO = appProblemService.registProblem(newEssayProblem);

        // then
        assertNotNull(testProblemDTO);
        log.info(testProblemDTO.toString());
    }
}