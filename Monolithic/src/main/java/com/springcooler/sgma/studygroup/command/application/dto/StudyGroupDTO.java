package com.springcooler.sgma.studygroup.command.application.dto;

import com.springcooler.sgma.studygroup.command.domain.aggregate.StudyGroupStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyGroupDTO {
    private Long groupId;
    private String groupName;
    private StudyGroupStatus activeStatus;
    private Integer groupMembers;
    private Long userId;
    private Integer studyGroupCategoryId;
}
