package com.springcooler.sgma.studygroupmember.command.application.dto;

import com.springcooler.sgma.studygroupmember.command.domain.aggregate.GroupRole;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.StudyGroupMemberStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyGroupMemberDTO {
    private Long memberId;
    private LocalDateTime memberEnrolledAt;
    private LocalDateTime memberWithdrawnAt;
    private StudyGroupMemberStatus memberStatus;
    private Long userId;
    private Long groupId;
    private GroupRole groupRole;
}
