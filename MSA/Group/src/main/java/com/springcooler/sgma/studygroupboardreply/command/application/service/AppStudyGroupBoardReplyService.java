package com.springcooler.sgma.studygroupboardreply.command.application.service;

import com.springcooler.sgma.studygroupboardreply.command.application.dto.StudyGroupBoardReplyDTO;

public interface AppStudyGroupBoardReplyService {

    // 대댓글 작성
    StudyGroupBoardReplyDTO registStudyGroupBoardReply(StudyGroupBoardReplyDTO newReply);

    // 대댓글 수정
    StudyGroupBoardReplyDTO modifyStudyGroupBoardReply(StudyGroupBoardReplyDTO modifyReply);

    // 대댓글 삭제
    void deleteStudyGroupBoardReply(Long replyId);

}
