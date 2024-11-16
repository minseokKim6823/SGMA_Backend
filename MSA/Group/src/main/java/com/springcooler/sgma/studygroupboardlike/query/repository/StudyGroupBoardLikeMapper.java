package com.springcooler.sgma.studygroupboardlike.query.repository;

import com.springcooler.sgma.studygroupboardlike.query.dto.StudyGroupBoardLikeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyGroupBoardLikeMapper {

    // 게시글 아이디로 좋아요 조회
    List<StudyGroupBoardLikeDTO> findStudyGroupBoardLikesByBoardId(Long boardId);

    // 그룹원 아이디로 좋아요 조회
    List<StudyGroupBoardLikeDTO> findStudyGroupBoardLikesByMemberId(Long memberId);

}
