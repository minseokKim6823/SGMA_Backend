package com.springcooler.sgma.studygroup.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.studygroup.command.domain.aggregate.StudyGroupStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestStudyGroupVO {

    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("group_name")
    private String groupName;

    @Enumerated(EnumType.STRING)
    @JsonProperty("active_status")
    private StudyGroupStatus activeStatus;

    @JsonProperty("group_members")
    private Integer groupMembers;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("category_id")
    private Integer studyGroupCategoryId;

}
