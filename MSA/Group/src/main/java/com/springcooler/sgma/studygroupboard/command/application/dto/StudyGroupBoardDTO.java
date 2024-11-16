package com.springcooler.sgma.studygroupboard.command.application.dto;

import com.springcooler.sgma.studygroupboard.command.domain.aggregate.StudyGroupBoardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyGroupBoardDTO {
    private Long studyGroupBoardId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StudyGroupBoardStatus activeStatus;
    private Integer likes;
    private Long memberId;
    private Long groupId;
}
