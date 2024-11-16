package com.springcooler.sgma.recruitmentboardlike.command.domain.service;

import com.springcooler.sgma.recruitmentboardlike.command.application.dto.RecruitmentBoardLikeDTO;
import com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.RestStatus;

public interface DomainRecruitmentBoardLikeService {

    boolean isValidDTO(RestStatus restStatus, RecruitmentBoardLikeDTO recruitmentBoardLikeDTO);

}
