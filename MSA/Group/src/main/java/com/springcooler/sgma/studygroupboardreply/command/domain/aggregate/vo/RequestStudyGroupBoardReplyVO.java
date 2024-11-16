package com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.StudyGroupBoardReplyStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestStudyGroupBoardReplyVO {

    @JsonProperty("reply_id")
    private Long studyGroupBoardReplyId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @JsonProperty("active_status")
    private StudyGroupBoardReplyStatus activeStatus;

    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("comment_id")
    private Long studyGroupBoardCommentId;

}
