package com.springcooler.sgma.user.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecruitmentBoardReplyDTO {
    @JsonProperty("reply_id")
    private Long replyId;

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

    @JsonProperty("comment_id")
    private Long commentId;

    @JsonProperty("recruitment_board_title")
    private String recruitmentBoardTitle;  // 모집글 제목 추가

    @JsonProperty("study_group_name")
    private String studyGroupName;  // 스터디 그룹명 추가

    @JsonProperty("recruitment_status")
    private String recruitmentStatus; // 모집글 상태 추가 (예: 모집 중, 모집 완료 등)
}
