package com.springcooler.sgma.user.query.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserCommentsAndRepliesDTO {
    private List<RecruitmentBoardCommentDTO> comments;
    private List<RecruitmentBoardReplyDTO> replies;
}