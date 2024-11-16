package com.springcooler.sgma.studygroupboardlike.command.application.service;

import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;
import com.springcooler.sgma.studygroupboardlike.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringBootTest
class StudyGroupBoardLikeServiceTests {

    @Autowired
    AppStudyGroupBoardLikeService studyGroupBoardLikeService;

    @DisplayName("좋아요 생성 테스트")
    @Test
    void testRegistStudyGroupBoardLike() {
        //Given
        StudyGroupBoardLikeDTO newLike = StudyGroupBoardLikeDTO.builder()
                .studyGroupBoardId(1L)
                .memberId(1L)
                .build();

        //When
        StudyGroupBoardLikeDTO like = studyGroupBoardLikeService.registStudyGroupBoardLike(newLike);
        if (like != null) {
            log.info(like.toString());
        }

        //Then
        Assertions.assertNotNull(like);
    }

    @DisplayName("좋아요 취소 테스트")
    @Test
    void testDeleteStudyGroupBoardLike() {
        //Given
        Long boardId = 3L;
        Long memberId = 1L;

        //When
        studyGroupBoardLikeService.deleteStudyGroupBoardLike(boardId, memberId);

        //Then
        Assertions.assertThrows(CommonException.class,
                () -> studyGroupBoardLikeService.deleteStudyGroupBoardLike(boardId, memberId));
    }

}
