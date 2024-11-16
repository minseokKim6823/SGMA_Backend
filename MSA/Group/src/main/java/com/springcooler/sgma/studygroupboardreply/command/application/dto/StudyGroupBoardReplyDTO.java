package com.springcooler.sgma.studygroupboardreply.command.application.dto;

import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.StudyGroupBoardReplyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyGroupBoardReplyDTO {
    private Long studyGroupBoardReplyId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StudyGroupBoardReplyStatus activeStatus;
    private Long memberId;
    private Long studyGroupBoardCommentId;
}
