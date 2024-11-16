package com.springcooler.sgma.studygroupboardlike.query.service;

import com.springcooler.sgma.studygroupboardlike.query.dto.StudyGroupBoardLikeDTO;

import java.util.List;

public interface StudyGroupBoardLikeService {

    // 게시글 아이디로 좋아요 조회
    List<StudyGroupBoardLikeDTO> findStudyGroupBoardLikesByBoardId(Long boardId);

    // 그룹원 아이디로 좋아요 조회
    List<StudyGroupBoardLikeDTO> findStudyGroupBoardLikesByMemberId(Long memberId);

}
