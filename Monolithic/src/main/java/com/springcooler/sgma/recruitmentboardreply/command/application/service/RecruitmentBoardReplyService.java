package com.springcooler.sgma.recruitmentboardreply.command.application.service;

import com.springcooler.sgma.recruitmentboardcomment.command.application.dto.RecruitmentBoardCommentCommandDTO;

import com.springcooler.sgma.recruitmentboardreply.command.application.dto.RecruitmentBoardReplyDTO;
import com.springcooler.sgma.recruitmentboardreply.command.domain.aggregate.RecruitmentBoardReply;

public interface RecruitmentBoardReplyService {
    RecruitmentBoardReply createRecruitBoardReply(RecruitmentBoardReplyDTO recruitmentBoardReplyDTO);

    RecruitmentBoardReply updateRecruitmentReply(Long recruitmentBoardCommentId, RecruitmentBoardReplyDTO recruitmentBoardReplyDTO);

    void deleteRecruitmentReply(Long recruitmentBoardCommentId);
}
