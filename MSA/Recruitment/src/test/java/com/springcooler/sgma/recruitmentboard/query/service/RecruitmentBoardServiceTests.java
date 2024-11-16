package com.springcooler.sgma.recruitmentboard.query.service;


import com.springcooler.sgma.recruitmentboard.query.dto.RecruitmentBoardDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest
class RecruitmentBoardServiceTests {

    @Autowired
    RecruitmentBoardService recruitmentBoardService;

    @DisplayName("스터디 그룹 모집글 전체 조회 테스트")
    @Test
    void testFindAllRecruitmentBoards() {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<RecruitmentBoardDTO> recruitmentBoards = recruitmentBoardService.findAllRecruitmentBoards();
                    recruitmentBoards.forEach(System.out::println);
                }
        );
    }

    @DisplayName("스터디 그룹 모집글 단건 조회 테스트")
    @Test
    void testFindRecruitmentBoardByBoardId() {

        Assertions.assertDoesNotThrow(
                () -> {
                    RecruitmentBoardDTO recruitmentBoard =
                            recruitmentBoardService.findRecruitmentBoardByBoardId(1L);
                    System.out.println(recruitmentBoard);
                }
        );

    }
}



