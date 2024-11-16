package com.springcooler.sgma.studygroupboardcomment.command.application.service;

import com.springcooler.sgma.studygroupboardcomment.command.application.dto.StudyGroupBoardCommentDTO;
import com.springcooler.sgma.studygroupboardcomment.common.exception.CommonException;
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
class StudyGroupBoardCommentServiceTests {

    @Autowired
    private AppStudyGroupBoardCommentService studyGroupBoardCommentService;

    @DisplayName("자유게시글 댓글 작성")
    @Test
    void testRegistStudyGroupBoardComment() {
        //Given
        StudyGroupBoardCommentDTO newComment = StudyGroupBoardCommentDTO.builder()
                .content("테스트용 댓글")
                .memberId(1L)
                .studyGroupBoardId(1L)
                .build();

        //When
        StudyGroupBoardCommentDTO comment = studyGroupBoardCommentService.registStudyGroupBoardComment(newComment);
        if(comment != null) {
            log.info(comment.toString());
        }

        //Then
        Assertions.assertNotNull(comment);
    }

    @DisplayName("자유게시글 댓글 수정")
    @Test
    void testModifyStudyGroupBoardComment() {
        //Given
        StudyGroupBoardCommentDTO modifyComment = StudyGroupBoardCommentDTO.builder()
                .studyGroupBoardCommentId(1L)
                .content("바뀐 댓글")
                .build();

        //When
        StudyGroupBoardCommentDTO comment = studyGroupBoardCommentService.modifyStudyGroupBoardComment(modifyComment);
        if(comment != null) {
            log.info(comment.toString());
        }

        //Then
        Assertions.assertNotNull(comment);
    }

    @DisplayName("자유게시글 댓글 삭제")
    @Test
    void testDeleteStudyGroupBoardComment() {
        //Given
        Long commentId = 1L;

        //When
        studyGroupBoardCommentService.deleteStudyGroupBoardComment(commentId);

        //Then
        Assertions.assertThrows(CommonException.class,
                () -> studyGroupBoardCommentService.deleteStudyGroupBoardComment(commentId));
    }

}
