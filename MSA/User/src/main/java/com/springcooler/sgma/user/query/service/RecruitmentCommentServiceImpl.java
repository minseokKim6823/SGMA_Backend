package com.springcooler.sgma.user.query.service;

import com.springcooler.sgma.user.common.exception.CommonException;
import com.springcooler.sgma.user.common.exception.ErrorCode;
import com.springcooler.sgma.user.query.dto.RecruitmentBoardCommentDTO;
import com.springcooler.sgma.user.query.dto.RecruitmentBoardReplyDTO;
import com.springcooler.sgma.user.query.dto.UserCommentsAndRepliesDTO;
import com.springcooler.sgma.user.query.repository.RecruitmentCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecruitmentCommentServiceImpl implements RecruitmentCommentService {

    private final RecruitmentCommentMapper recruitmentCommentMapper;

    @Autowired
    public RecruitmentCommentServiceImpl(RecruitmentCommentMapper recruitmentCommentMapper) {
        this.recruitmentCommentMapper = recruitmentCommentMapper;
    }

    @Override
    public UserCommentsAndRepliesDTO getCommentsAndRepliesByUserId(Long userId) {
        List<RecruitmentBoardCommentDTO> comments = recruitmentCommentMapper.findCommentsByUserId(userId);
        List<RecruitmentBoardReplyDTO> replies = recruitmentCommentMapper.findRepliesByUserId(userId);

        // 댓글과 대댓글이 모두 없는 경우 예외 처리
        if ((comments == null || comments.isEmpty()) && (replies == null || replies.isEmpty())) {
            throw new CommonException(ErrorCode.NOT_FOUND_RECRUITMENT_BOARD_COMMENT);
        }

        // 기존 Map 사용 대신, List로 그대로 반환
        UserCommentsAndRepliesDTO result = new UserCommentsAndRepliesDTO();
        result.setComments(comments);  // List를 그대로 설정
        result.setReplies(replies);    // List를 그대로 설정

        return result;
    }

}
