package com.springcooler.sgma.recruitmentboard.command.application.service;

import com.springcooler.sgma.recruitmentboard.command.application.dto.RecruitmentBoardCommandDTO;
import com.springcooler.sgma.recruitmentboard.command.domain.aggregate.RecruitmentBoard;

public interface RecruitmentBoardCommandService {
    RecruitmentBoard createStudyGroupApplicant(RecruitmentBoardCommandDTO recruitmentBoardCommandDTO);

    RecruitmentBoardCommandDTO updateStudyGroupApplicant(Long recruitmentBoardId,RecruitmentBoardCommandDTO recruitmentBoardCommandDTO);

    boolean deleteStudyGroupApplicant(Long recruitmentBoardId);
}
