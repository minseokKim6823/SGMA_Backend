package com.springcooler.sgma.user.query.service;

import com.springcooler.sgma.user.common.exception.CommonException;
import com.springcooler.sgma.user.query.dto.RecruitmentBoardCommentDTO;
import com.springcooler.sgma.user.query.dto.RecruitmentBoardReplyDTO;
import com.springcooler.sgma.user.query.dto.UserCommentsAndRepliesDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecruitmentCommentServiceImplTests {

    @Autowired
    private RecruitmentCommentService recruitmentCommentService;

    @Test
    @DisplayName("사용자가 작성한 댓글과 대댓글 모두 조회 - 성공 케이스")
    public void testGetCommentsAndRepliesByUserId_SuccessWithCommentsAndReplies() {
        // given
        Long userId = 3L; // DB에 댓글과 대댓글을 모두 작성한 사용자 ID

        // when
        UserCommentsAndRepliesDTO result = recruitmentCommentService.getCommentsAndRepliesByUserId(userId);

        // then
        assertNotNull(result);
        assertFalse(result.getComments().isEmpty(), "댓글 리스트가 비어있으면 안 됩니다.");
        assertFalse(result.getReplies().isEmpty(), "대댓글 리스트가 비어있으면 안 됩니다.");

        // 각 댓글과 대댓글을 추가로 검증
        for (RecruitmentBoardCommentDTO comment : result.getComments()) {
            assertNotNull(comment.getCommentId());
            assertNotNull(comment.getContent());
        }

        for (RecruitmentBoardReplyDTO reply : result.getReplies()) {
            assertNotNull(reply.getReplyId());
            assertNotNull(reply.getContent());
        }
    }

    @Test
    @DisplayName("사용자가 작성한 대댓글만 조회 - 성공 케이스")
    public void testGetCommentsAndRepliesByUserId_SuccessWithOnlyReplies() {
        // given
        Long userId = 2L; // DB에 댓글 없이 대댓글만 작성한 사용자 ID

        // when
        UserCommentsAndRepliesDTO result = recruitmentCommentService.getCommentsAndRepliesByUserId(userId);

        // then
        assertNotNull(result);
        assertTrue(result.getComments().isEmpty(), "댓글 리스트가 비어 있어야 합니다.");
        assertFalse(result.getReplies().isEmpty(), "대댓글 리스트가 비어있으면 안 됩니다.");

        // 대댓글을 추가로 검증
        for (RecruitmentBoardReplyDTO reply : result.getReplies()) {
            assertNotNull(reply.getReplyId());
            assertNotNull(reply.getContent());
        }
    }

    @Test
    @DisplayName("사용자가 작성한 댓글만 조회 - 성공 케이스")
    public void testGetCommentsAndRepliesByUserId_SuccessWithOnlyComments() {
        // given
        Long userId = 1L; // DB에 대댓글 없이 댓글만 작성한 사용자 ID

        // when
        UserCommentsAndRepliesDTO result = recruitmentCommentService.getCommentsAndRepliesByUserId(userId);

        // then
        assertNotNull(result);
        assertFalse(result.getComments().isEmpty(), "댓글 리스트가 비어있으면 안 됩니다.");
        assertTrue(result.getReplies().isEmpty(), "대댓글 리스트가 비어 있어야 합니다.");

        // 댓글을 추가로 검증
        for (RecruitmentBoardCommentDTO comment : result.getComments()) {
            assertNotNull(comment.getCommentId());
            assertNotNull(comment.getContent());
        }
    }

    @Test
    @DisplayName("사용자가 작성한 댓글과 대댓글 모두 없음 - 예외 발생")
    public void testGetCommentsAndRepliesByUserId_NotFound() {
        // given
        Long userId = 9999L; // DB에 댓글과 대댓글이 모두 없는 사용자 ID를 사용해야 합니다.

        // when & then
        Exception exception = assertThrows(CommonException.class, () -> {
            recruitmentCommentService.getCommentsAndRepliesByUserId(userId);
        });

        String expectedMessage = "모집 게시판 댓글이 존재하지 않습니다."; // ErrorCode에 정의된 메시지와 일치해야 합니다.
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
