package com.springcooler.sgma.studygroupboardreply.query.service;

import com.springcooler.sgma.studygroupboardreply.query.dto.StudyGroupBoardReplyDTO;
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
class StudyGroupBoardReplyServiceTests {

    @Autowired
    private StudyGroupBoardReplyService studyGroupBoardReplyService;

    @DisplayName("댓글 아이디로 대댓글 조회")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindStudyGroupBoardRepliesByCommentId(Long commentId) {
        Assertions.assertDoesNotThrow(() -> {
            List<StudyGroupBoardReplyDTO> replies =
                    studyGroupBoardReplyService.findStudyGroupBoardRepliesByCommentId(commentId);
            replies.forEach(x -> log.info(x.toString()));
        });
    }

    @DisplayName("그룹원 아이디로 대댓글 조회")
    @ParameterizedTest
    @ValueSource(longs = 4L)
    void testFindStudyGroupBoardRepliesByMemberId(Long memberId) {
        Assertions.assertDoesNotThrow(() -> {
            List<StudyGroupBoardReplyDTO> replies =
                    studyGroupBoardReplyService.findStudyGroupBoardRepliesByMemberId(memberId);
            replies.forEach(x -> log.info(x.toString()));
        });
    }

}
