package com.springcooler.sgma.studygroupboard.query.service;

import com.springcooler.sgma.studygroupboard.query.dto.PageDTO;
import com.springcooler.sgma.studygroupboard.query.dto.StudyGroupBoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class StudyGroupBoardServiceTests {

    @Autowired
    private StudyGroupBoardService studyGroupBoardService;

    @DisplayName("게시글 그룹별 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = 2L)
    void testFindStudyGroupBoardsByGroupId(Long groupId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    PageDTO<StudyGroupBoardDTO> page =
                            studyGroupBoardService.findStudyGroupBoardsByGroupId(groupId, 1);
                    page.getElements().forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("게시글 그룹원별 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindStudyGroupBoardsByMemberId(Long memberId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    PageDTO<StudyGroupBoardDTO> page =
                            studyGroupBoardService.findStudyGroupBoardsByMemberId(memberId, 1);
                    page.getElements().forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("게시글 제목으로 조회 테스트")
    @ParameterizedTest
    @ValueSource(strings = "험")
    void testFindStudyGroupBoardsByTitle(String title) {
        Assertions.assertDoesNotThrow(
                () -> {
                    PageDTO<StudyGroupBoardDTO> page =
                            studyGroupBoardService.findStudyGroupBoardsByTitle(1L, title, 1);
                    page.getElements().forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("게시글 내용으로 조회 테스트")
    @ParameterizedTest
    @ValueSource(strings = "이")
    void testFindStudyGroupBoardsByContent(String content) {
        Assertions.assertDoesNotThrow(
                () -> {
                    PageDTO<StudyGroupBoardDTO> page =
                            studyGroupBoardService.findStudyGroupBoardsByContent(1L, content, 1);
                    page.getElements().forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("게시글 단건 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = 3L)
    void testFindStudyGroupBoardByBoardId(Long boardId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    StudyGroupBoardDTO board = studyGroupBoardService.findStudyGroupBoardByBoardId(boardId);
                    log.info(board.toString());
                }
        );
    }

}
