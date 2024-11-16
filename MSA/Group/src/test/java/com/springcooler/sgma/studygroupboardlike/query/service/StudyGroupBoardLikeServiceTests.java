package com.springcooler.sgma.studygroupboardlike.query.service;

import com.springcooler.sgma.studygroupboardlike.query.dto.StudyGroupBoardLikeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class StudyGroupBoardLikeServiceTests {

    @Autowired
    StudyGroupBoardLikeService studyGroupBoardLikeService;

    @DisplayName("게시글 아이디로 좋아요 조회")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindStudyGroupBoardLikesByBoardId(Long boardId) {
        Assertions.assertDoesNotThrow(() -> {
            List<StudyGroupBoardLikeDTO> likes =
                    studyGroupBoardLikeService.findStudyGroupBoardLikesByBoardId(boardId);
            likes.forEach(x -> log.info(x.toString()));
        });
    }

    @DisplayName("그룹원 아이디로 좋아요 조회")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindStudyGroupBoardLikesByMemberId(Long memberId) {
        Assertions.assertDoesNotThrow(() -> {
            List<StudyGroupBoardLikeDTO> likes =
                    studyGroupBoardLikeService.findStudyGroupBoardLikesByMemberId(memberId);
            likes.forEach(x -> log.info(x.toString()));
        });
    }

}
