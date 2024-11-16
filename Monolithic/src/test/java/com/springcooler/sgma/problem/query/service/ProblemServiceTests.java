package com.springcooler.sgma.problem.query.service;

import com.springcooler.sgma.problem.query.dto.ProblemAndChoiceDTO;
import com.springcooler.sgma.problem.query.dto.ProblemDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ProblemServiceTests {

    @Autowired
    private ProblemService problemService;

    @DisplayName("전체 문제 조회")
    @Test
    void testFindAllProblems() {
        List<ProblemDTO> problems = problemService.findAllProblems();
        Assertions.assertNotNull(problems);
        problems.forEach(System.out::println);
    }

    @DisplayName("스케쥴 아이디로 문제 조회")
    @ParameterizedTest
    @ValueSource(longs = 3L)
    void testFindProblemsByScheduleId(long scheduleId) {
        List<ProblemDTO> problems = problemService.findProblemsByScheduleId(scheduleId);
        Assertions.assertNotNull(problems);
        problems.forEach(System.out::println);
    }

    private static Stream<Arguments> getParticipantAndScheduleInfo(){
        return Stream.of(
                Arguments.of(3,1)
        );
    }
    @DisplayName("참가자 아이디와 스케쥴 아이디로 문제 조회")
    @ParameterizedTest
    @MethodSource("getParticipantAndScheduleInfo")
    void testFindProblemsByParticipantIdAndScheduleId(long scheduleId, long participantId) {
        List<ProblemDTO> problems = problemService.findProblemsByScheduleIdAndParticipantId(scheduleId, participantId);

        problems.forEach(x->log.info("problems: {}", problems));
    }

    @DisplayName("문제 ID로 문제와 선지 정보 조회")
    @Test
    void testFindProblemAndChoiceByProblemId(){

        //given
        long problemId = 1L;

        // when
        ProblemAndChoiceDTO problemAndChoiceDTO = problemService.findProblemAndChoiceByProblemId(problemId);

        // then
        assertNotNull(problemAndChoiceDTO);
        log.info("problemAndChoiceDTO: {}", problemAndChoiceDTO);
    }
}