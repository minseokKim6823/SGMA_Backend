package com.springcooler.sgma.studygroup.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.GroupRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyStudyGroupDTO {

    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("category_id")
    private Integer studyGroupCategoryId;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("member_enrolled_at")
    private LocalDateTime memberEnrolledAt;

    @Enumerated(EnumType.STRING)
    @JsonProperty("group_role")
    private GroupRole groupRole;

}
