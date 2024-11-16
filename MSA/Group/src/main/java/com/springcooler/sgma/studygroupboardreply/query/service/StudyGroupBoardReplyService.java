package com.springcooler.sgma.studygroupboardreply.query.service;

import com.springcooler.sgma.studygroupboardreply.query.dto.StudyGroupBoardReplyDTO;

import java.util.List;

public interface StudyGroupBoardReplyService {

    // 댓글 아이디로 대댓글 조회
    List<StudyGroupBoardReplyDTO> findStudyGroupBoardRepliesByCommentId(Long commentId);

    // 그룹원 아이디로 대댓글 조회
    List<StudyGroupBoardReplyDTO> findStudyGroupBoardRepliesByMemberId(Long memberId);

}
