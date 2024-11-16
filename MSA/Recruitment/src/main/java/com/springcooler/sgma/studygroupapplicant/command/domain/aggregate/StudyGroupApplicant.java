package com.springcooler.sgma.studygroupapplicant.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StudyGroupApplicantId.class)
public class StudyGroupApplicant {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "recruitment_board_id")
    private Long recruitmentBoardId;

    @Column(name="application_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @Column(name="group_id",nullable = false)
    private Long groupId;
}
