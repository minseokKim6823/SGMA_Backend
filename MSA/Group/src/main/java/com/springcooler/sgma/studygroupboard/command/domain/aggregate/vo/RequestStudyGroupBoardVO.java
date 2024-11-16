package com.springcooler.sgma.studygroupboard.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.StudyGroupBoardStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestStudyGroupBoardVO {

    @JsonProperty("board_id")
    private Long studyGroupBoardId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @JsonProperty("active_status")
    private StudyGroupBoardStatus activeStatus;

    @JsonProperty("likes")
    private Integer likes;

    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("group_id")
    private Long groupId;

}
