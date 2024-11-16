package com.springcooler.sgma.studygroupboardcomment.query.service;

import com.springcooler.sgma.studygroupboardcomment.query.dto.StudyGroupBoardCommentDTO;

import java.util.List;

public interface StudyGroupBoardCommentService {

    // 게시글별 댓글 전체 조회
    List<StudyGroupBoardCommentDTO> findStudyGroupBoardCommentsByBoardId(Long boardId);

    // 그룹원별 댓글 전체 조회
    List<StudyGroupBoardCommentDTO> findStudyGroupBoardCommentsByMemberId(Long memberId);

}
