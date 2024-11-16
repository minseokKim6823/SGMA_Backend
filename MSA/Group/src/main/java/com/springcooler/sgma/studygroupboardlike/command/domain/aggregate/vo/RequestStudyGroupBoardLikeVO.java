package com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestStudyGroupBoardLikeVO {

    @JsonProperty("board_id")
    private Long studyGroupBoardId;

    @JsonProperty("member_id")
    private Long memberId;

}
