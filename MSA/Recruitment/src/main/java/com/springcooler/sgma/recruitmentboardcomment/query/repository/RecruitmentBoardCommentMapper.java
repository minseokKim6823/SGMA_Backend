package com.springcooler.sgma.recruitmentboardcomment.query.repository;

import com.springcooler.sgma.recruitmentboardcomment.query.dto.RecruitmentBoardCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitmentBoardCommentMapper {

    List<RecruitmentBoardCommentDTO> findStudyGroupRecruitmentCommentByBoardId(Long recruitmentBoardId);

}
