package com.springcooler.sgma.studygroupboardcomment.query.service;

import com.springcooler.sgma.studygroupboardcomment.query.dto.StudyGroupBoardCommentDTO;
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
class StudyGroupBoardCommentServiceTests {

    @Autowired
    private StudyGroupBoardCommentService studyGroupBoardCommentService;

    @DisplayName("자유게시글 댓글 조회(게시글 아이디)")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindStudyGroupBoardCommentsByBoardId(Long boardId) {
        Assertions.assertDoesNotThrow(() -> {
                    List<StudyGroupBoardCommentDTO> comments =
                            studyGroupBoardCommentService.findStudyGroupBoardCommentsByBoardId(boardId);
                    comments.forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("자유게시글 댓글 조회(그룹원 아이디)")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindStudyGroupBoardCommentsByMemberId(Long memberId) {
        Assertions.assertDoesNotThrow(() -> {
                    List<StudyGroupBoardCommentDTO> comments =
                            studyGroupBoardCommentService.findStudyGroupBoardCommentsByMemberId(memberId);
                    comments.forEach(x -> log.info(x.toString()));
                }
        );
    }

}
