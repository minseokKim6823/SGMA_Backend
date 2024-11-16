package com.springcooler.sgma.studygroupboardcomment.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.StudyGroupBoardCommentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudyGroupBoardCommentDTO {

    @JsonProperty("comment_id")
    private Long studyGroupBoardCommentId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @JsonProperty("active_status")
    private StudyGroupBoardCommentStatus activeStatus;

    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("board_id")
    private Long studyGroupBoardId;

}
