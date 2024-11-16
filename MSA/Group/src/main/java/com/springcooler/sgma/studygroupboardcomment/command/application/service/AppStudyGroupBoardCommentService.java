package com.springcooler.sgma.studygroupboardcomment.command.application.service;

import com.springcooler.sgma.studygroupboardcomment.command.application.dto.StudyGroupBoardCommentDTO;

public interface AppStudyGroupBoardCommentService {

    // 댓글 작성
    StudyGroupBoardCommentDTO registStudyGroupBoardComment(StudyGroupBoardCommentDTO newComment);

    // 댓글 수정
    StudyGroupBoardCommentDTO modifyStudyGroupBoardComment(StudyGroupBoardCommentDTO modifyComment);

    // 댓글 삭제
    void deleteStudyGroupBoardComment(Long commentId);

}
