package com.springcooler.sgma.recruitmentboardlike.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RecruitmentBoardLikeDTO {
    private Long recruitmentBoardId;
    private Long userId;
}
