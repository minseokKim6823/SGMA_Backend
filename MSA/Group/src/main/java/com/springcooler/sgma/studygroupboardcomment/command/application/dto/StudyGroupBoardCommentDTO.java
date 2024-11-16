package com.springcooler.sgma.studygroupboardcomment.command.application.dto;

import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.StudyGroupBoardCommentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyGroupBoardCommentDTO {
    private Long studyGroupBoardCommentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StudyGroupBoardCommentStatus activeStatus;
    private Long memberId;
    private Long studyGroupBoardId;
}
