package com.springcooler.sgma.studygroupboardreply.query.repository;

import com.springcooler.sgma.studygroupboardreply.query.dto.StudyGroupBoardReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyGroupBoardReplyMapper {

    // 댓글 아이디로 대댓글 조회
    List<StudyGroupBoardReplyDTO> findStudyGroupBoardRepliesByCommentId(Long commentId);

    // 그룹원 아이디로 대댓글 조회
    List<StudyGroupBoardReplyDTO> findStudyGroupBoardRepliesByMemberId(Long memberId);

}
