package com.springcooler.sgma.problem.query.service;

import com.springcooler.sgma.problem.query.dto.ProblemDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ProblemServiceTests {

    @Autowired
    private ProblemService problemService;

    @DisplayName("전체 문제 조회")
    @Test
    void testFindAllProblems(){

        // given

        // when
        List<ProblemDTO> problems = problemService.findAllProblems();

        // then
        assertNotNull(problems);
        problems.forEach(x->log.info(x.toString()));
    }


    @DisplayName("문제 ID로 문제 조회")
    @Test
    void testFindProblemById(){

        // given
        Long problemId = 1L;

        // when
        ProblemDTO problem = problemService.findProblemById(problemId);

        // then
        assertNotNull(problem);
        log.info(problem.toString());
    }

    @DisplayName("스케쥴ID로 문제 조회")
    @Test
    void testFindProblemsByScheduleId(){

        // given
        Long scheduleId =2L;

        // when
        List<ProblemDTO> problems = problemService.findProblemsByScheduleId(scheduleId);

        // then
        assertNotNull(problems);
        problems.forEach(x->log.info(x.toString()));
    }
}