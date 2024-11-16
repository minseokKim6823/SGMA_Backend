package com.springcooler.sgma.choice.command.application.service;


import com.springcooler.sgma.choice.command.application.dto.ChoiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Transactional
@Slf4j
class AppChoiceServiceTests {

    @Autowired
    private AppChoiceService appChoiceService;
//    @DisplayName("선지 등록 테스트")
//    @Test
//    void testRegistChoice(){
//
//        // given
//        long problemId = 12L;
//        ChoiceDTO[] choices = new ChoiceDTO[4];
//        for (int i = 0; i < 4; i++) {
//            choices[i] = new ChoiceDTO(problemId, i+1, "선지등록테스트"+(i+1));
//        }
//
//        // when
//        for (int i = 0; i < 4; i++) {
//            appChoiceService.registChoice(choices[i]);
//        }
//        // then
//    }

    @DisplayName("선지 수정 테스트")
    @Test
    void testModifyChoice(){

        // given
        long problemId = 1L;
        int choiceNum = 1;
        ChoiceDTO modifyChoice = new ChoiceDTO(problemId, choiceNum,"선지 수정 테스트");

        // when
        ChoiceDTO modifiedChoice = appChoiceService.modifyChoice(modifyChoice);

        // then
        assertNotEquals(modifyChoice, modifiedChoice);
        log.info("modifiedChoice: {}", modifiedChoice);
    }

}