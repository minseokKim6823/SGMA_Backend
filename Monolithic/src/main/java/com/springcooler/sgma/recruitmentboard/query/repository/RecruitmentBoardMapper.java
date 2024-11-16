package com.springcooler.sgma.recruitmentboard.query.repository;

import com.springcooler.sgma.recruitmentboard.query.dto.RecruitmentBoardDTO;


import java.util.List;


public interface RecruitmentBoardMapper {
    List<RecruitmentBoardDTO> studyGroupRecruitment();

    RecruitmentBoardDTO selectStudyGroupApplicantDTO(Long recruitmentBoardId);

}
