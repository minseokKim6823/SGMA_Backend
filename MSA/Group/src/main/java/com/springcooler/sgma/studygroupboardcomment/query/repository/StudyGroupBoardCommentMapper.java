package com.springcooler.sgma.studygroupboardcomment.query.repository;

import com.springcooler.sgma.studygroupboardcomment.query.dto.StudyGroupBoardCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyGroupBoardCommentMapper {

    List<StudyGroupBoardCommentDTO> findStudyGroupBoardCommentsByBoardId(Long boardId);

    List<StudyGroupBoardCommentDTO> findStudyGroupBoardCommentsByMemberId(Long memberId);

}
