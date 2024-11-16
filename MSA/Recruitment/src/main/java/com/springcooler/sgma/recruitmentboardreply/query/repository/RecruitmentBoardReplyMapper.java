package com.springcooler.sgma.recruitmentboardreply.query.repository;

import com.springcooler.sgma.recruitmentboardreply.query.dto.RecruitmentBoardReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitmentBoardReplyMapper {
    List<RecruitmentBoardReplyDTO> findRecruitmentBoardReplies();

    List<RecruitmentBoardReplyDTO> findRecruitmentBoardRepliesByBoardId(Long recruitmentBoardId);
}
