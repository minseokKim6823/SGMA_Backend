package com.springcooler.sgma.recruitmentboardlike.command.application.service;


import com.springcooler.sgma.recruitmentboardlike.command.application.dto.RecruitmentBoardLikeDTO;

public interface AppRecruitmentBoardLikeService {

    RecruitmentBoardLikeDTO registRecruitmentBoardLike(RecruitmentBoardLikeDTO like);

    void deleteRecruitmentBoardLike(Long recruitmentId, Long userId);

}
