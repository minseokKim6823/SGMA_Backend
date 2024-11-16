package com.springcooler.sgma.user.query.service;

import com.springcooler.sgma.user.query.dto.RecruitmentBoardCommentDTO;
import com.springcooler.sgma.user.query.dto.UserCommentsAndRepliesDTO;

import java.util.List;

public interface RecruitmentCommentService {
    UserCommentsAndRepliesDTO getCommentsAndRepliesByUserId(Long userId);
}
