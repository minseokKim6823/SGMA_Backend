package com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseRecruitmentBoardLikeVO {

    @JsonProperty("recruitment_board_id")
    private Long recruitmentBoardId;

    @JsonProperty("user_id")
    private Long userId;

}
