package com.springcooler.sgma.recruitmentboardlike.query.service;

import com.springcooler.sgma.recruitmentboardlike.query.dto.RecruitmentBoardLikeDTO;
import com.springcooler.sgma.recruitmentboardlike.query.repository.RecruitmentBoardLikeMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RecruitmentBoardLikeService {
    private final RecruitmentBoardLikeMapper recruitmentBoardLikeMapper;

    @Autowired
    public RecruitmentBoardLikeService(RecruitmentBoardLikeMapper recruitmentBoardLikeMapper) {
        this.recruitmentBoardLikeMapper = recruitmentBoardLikeMapper;
    }

    public List<RecruitmentBoardLikeDTO> findAllRecruitmentBoardLike() {
        List<RecruitmentBoardLikeDTO> likes = recruitmentBoardLikeMapper.findrecruitmentBoardLike();
        log.info("=====================" + likes);
        return likes;
    }

    public List<RecruitmentBoardLikeDTO> findrecruitmentBoardLikeByUserId(Long userId) {
        List<RecruitmentBoardLikeDTO> likes = recruitmentBoardLikeMapper.findrecruitmentBoardLikeByUserId(userId);
        return likes;
    }

    public List<RecruitmentBoardLikeDTO> findrecruitmentBoardLikeByRecruitmentBoardId(Long recruitmentBoardId) {
        List<RecruitmentBoardLikeDTO> likes = recruitmentBoardLikeMapper.findrecruitmentBoardLikeByRecruitmentBoardId(recruitmentBoardId);
        return likes;
    }
}
