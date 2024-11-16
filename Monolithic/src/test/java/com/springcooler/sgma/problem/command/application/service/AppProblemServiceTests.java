package com.springcooler.sgma.problem.command.application.service;

import com.springcooler.sgma.problem.command.application.dto.ProblemAndChoiceDTO;
import com.springcooler.sgma.problem.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
    @Slf4j
class AppProblemServiceTests {

    @Autowired
    private AppProblemService appProblemService;



    @DisplayName("문제 삭제 테스트")
    @Test
    void testDeleteProblem(){

        // given
        long deleteProblemId = 10L;

        // when
        appProblemService.deleteProblem(deleteProblemId);
        
        // then
        Assertions.assertThrows(CommonException.class, ()-> appProblemService.deleteProblem(deleteProblemId));
        System.out.println("삭제 완료");
    }

    @DisplayName("문제 및 선지 등록 테스트")
    @Test
    void testRegistProblemAndChoices(){

        // given
        long participantId = 9L;
        long scheduleId = 4L;
        String content = "문제 및 선지 등록 테스트";
        int answer = 1;
        List<String> choices = new ArrayList<>();
        choices.add("테스트코드 선지 1");
        choices.add("테스트코드 선지 2");
        choices.add("테스트코드 선지 3");
        choices.add("테스트코드 선지 4");
        ProblemAndChoiceDTO problemAndChoiceDTO = new ProblemAndChoiceDTO(null, participantId, scheduleId, content, answer, choices);

        // when
        ProblemAndChoiceDTO resultDTO =  appProblemService.registProblemAndChoice(problemAndChoiceDTO);
        // then
        assertNotNull(problemAndChoiceDTO);
        log.info("problemAndChoiceDTO.getContent(): {}", resultDTO.getContent());
        log.info("problemAndChoiceDTO.getChoices(): {}", resultDTO.getChoices());
    }
}
