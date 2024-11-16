package com.springcooler.sgma.studygroupboardlike.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudyGroupBoardLikeDTO {

    @JsonProperty("board_id")
    private Long studyGroupBoardId;

    @JsonProperty("member_id")
    private Long memberId;

}
