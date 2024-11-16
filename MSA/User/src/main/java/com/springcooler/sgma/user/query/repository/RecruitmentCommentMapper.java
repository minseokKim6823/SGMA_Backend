package com.springcooler.sgma.user.query.repository;

import com.springcooler.sgma.user.query.dto.RecruitmentBoardCommentDTO;
import com.springcooler.sgma.user.query.dto.RecruitmentBoardReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecruitmentCommentMapper {

    // 사용자가 작성한 댓글 조회
    List<RecruitmentBoardCommentDTO> findCommentsByUserId(@Param("userId") Long userId);

    // 사용자가 작성한 대댓글 조회
    List<RecruitmentBoardReplyDTO> findRepliesByUserId(@Param("userId") Long userId);
}