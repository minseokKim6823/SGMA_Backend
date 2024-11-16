package com.springcooler.sgma.choice.query.service;

import com.springcooler.sgma.choice.query.dto.ChoiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ChoiceServiceTests {

    @Autowired
    private ChoiceService choiceService;

    @DisplayName("선택지 전체 조회")
    @Test
    void testFindAllChoices(){

        // given

        // when
        List<ChoiceDTO> choices = choiceService.findAllChoices();

        // then
        assertNotNull(choices);

        choices.forEach(x->{log.info("choice : {}",x);});
    }

    @DisplayName("문제 ID로 선택지 조회")
    @Test
    void testFindChoiceByProblemId(){

        // given
        long problemId = 1L;

        // when
        List<ChoiceDTO> choices = choiceService.findChoicesByProblemId(problemId);

        // then
        assertNotNull(choices);
        choices.forEach(x->{log.info("choice : {}",x);});
    }
}