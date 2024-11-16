package com.springcooler.sgma.recruitmentboardcomment.query.repository;

import com.springcooler.sgma.recruitmentboardcomment.query.dto.RecruitmentBoardCommentDTO;

import java.util.List;

public interface RecruitmentBoardCommentMapper {

    List<RecruitmentBoardCommentDTO> studyGroupRecruitmentCommentDTO1(Long recruitmentBoardId);
}
