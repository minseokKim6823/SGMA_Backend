package com.springcooler.sgma.recruitmentboardcomment.query.service;

import com.springcooler.sgma.recruitmentboardcomment.query.dto.RecruitmentBoardCommentDTO;
import com.springcooler.sgma.recruitmentboardcomment.query.repository.RecruitmentBoardCommentMapper;
import com.springcooler.sgma.recruitmentboardcomment.common.exception.CommonException;
import com.springcooler.sgma.recruitmentboardcomment.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RecruitmentBoardCommentService {

    private final RecruitmentBoardCommentMapper recruitmentBoardCommentMapper;

    @Autowired
    public RecruitmentBoardCommentService(RecruitmentBoardCommentMapper recruitmentBoardCommentMapper) {
        this.recruitmentBoardCommentMapper = recruitmentBoardCommentMapper;
    }

    public List<RecruitmentBoardCommentDTO> findStudyGroupRecruitmentCommentByBoardId(Long recruitmentBoardId) {
        List<RecruitmentBoardCommentDTO> studyGroupRecruitment =
                recruitmentBoardCommentMapper.findStudyGroupRecruitmentCommentByBoardId(recruitmentBoardId);
        if(studyGroupRecruitment == null || studyGroupRecruitment.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_RECRUITMENT_BOARD_COMMENT);
        }
        return studyGroupRecruitment;
    }

}
