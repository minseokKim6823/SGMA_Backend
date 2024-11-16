package com.springcooler.sgma.studygroupapplicant.command.domain.aggregate.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestStudyGroupMemberVO {

    private Long userId;

    private Long groupId;

}
