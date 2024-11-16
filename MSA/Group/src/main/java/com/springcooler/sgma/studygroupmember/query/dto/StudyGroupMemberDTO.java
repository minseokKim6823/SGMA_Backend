package com.springcooler.sgma.studygroupmember.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.GroupRole;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.StudyGroupMemberStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyGroupMemberDTO {

    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("member_enrolled_at")
    private LocalDateTime memberEnrolledAt;

    @JsonProperty("member_withdrawn_at")
    private LocalDateTime memberWithdrawnAt;

    @Enumerated(EnumType.STRING)
    @JsonProperty("member_status")
    private StudyGroupMemberStatus memberStatus;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("group_id")
    private Long groupId;

    @Enumerated(EnumType.STRING)
    @JsonProperty("group_role")
    private GroupRole groupRole;

}
