package com.springcooler.sgma.user.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RecruitmentBoardCommentDTO {
    @JsonProperty("comment_id")
    private Long commentId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("active_status")
    private String activeStatus;

    @JsonProperty("anonymous_status")
    private String anonymousStatus;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("replies")
    private List<RecruitmentBoardReplyDTO> replies;
}
