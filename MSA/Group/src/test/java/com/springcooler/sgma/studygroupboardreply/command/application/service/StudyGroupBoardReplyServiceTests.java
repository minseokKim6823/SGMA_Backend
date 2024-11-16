package com.springcooler.sgma.studygroupboardreply.command.application.service;

import com.springcooler.sgma.studygroupboardreply.command.application.dto.StudyGroupBoardReplyDTO;
import com.springcooler.sgma.studygroupboardreply.common.exception.CommonException;
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
class StudyGroupBoardReplyServiceTests {

    @Autowired
    private AppStudyGroupBoardReplyService studyGroupBoardReplyService;

    @DisplayName("대댓글 작성")
    @Test
    void testRegistStudyGroupBoardReply() {
        //Given
        StudyGroupBoardReplyDTO newReply = StudyGroupBoardReplyDTO.builder()
                .content("테스트용 대댓글")
                .memberId(1L)
                .studyGroupBoardCommentId(1L)
                .build();

        //When
        StudyGroupBoardReplyDTO reply = studyGroupBoardReplyService.registStudyGroupBoardReply(newReply);
        if(reply != null) {
            log.info(reply.toString());
        }

        //Then
        Assertions.assertNotNull(reply);
    }

    @DisplayName("대댓글 수정")
    @Test
    void testModifyStudyGroupBoardReply() {
        //Given
        StudyGroupBoardReplyDTO modifyReply = StudyGroupBoardReplyDTO.builder()
                .studyGroupBoardReplyId(1L)
                .content("수정된 대댓글")
                .build();

        //When
        StudyGroupBoardReplyDTO reply = studyGroupBoardReplyService.modifyStudyGroupBoardReply(modifyReply);
        if(reply != null) {
            log.info(reply.toString());
        }

        //Then
        Assertions.assertNotNull(reply);
    }

    @DisplayName("대댓글 삭제")
    @Test
    void testDeleteStudyGroupBoardReply() {
        //Given
        Long replyId = 1L;

        //When
        studyGroupBoardReplyService.deleteStudyGroupBoardReply(replyId);


        //Then
        Assertions.assertThrows(CommonException.class,
                () -> studyGroupBoardReplyService.deleteStudyGroupBoardReply(replyId));
    }

}
