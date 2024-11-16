package com.springcooler.sgma.studygroupboardlike.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyGroupBoardLikeDTO {
    private Long studyGroupBoardId;
    private Long memberId;
}
