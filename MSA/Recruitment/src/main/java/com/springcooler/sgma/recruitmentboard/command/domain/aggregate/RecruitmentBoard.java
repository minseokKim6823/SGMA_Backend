package com.springcooler.sgma.recruitmentboard.command.domain.aggregate;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RecruitmentBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recruitment_board_id")
    private Long recruitmentBoardId;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at",columnDefinition = "TIMESTAMP",nullable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at",columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;
    @Column(name = "recruitment_start_time",columnDefinition = "TIMESTAMP",nullable = false)
    private java.sql.Timestamp recruitmentStartTime;
    @Column(name = "recruitment_end_time",columnDefinition = "TIMESTAMP",nullable = false)
    private java.sql.Timestamp recruitmentEndTime;

    @Column(name="active_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardActiveStatus activeStatus;

    @Column(nullable = false)
    private Integer likes;

    private Long group_id;

    private Integer study_group_category_id;


    public void checkAndUpdateStatus(Timestamp currentTime) {
        if (this.activeStatus == BoardActiveStatus.ACTIVE && currentTime.after(Timestamp.from(recruitmentEndTime.toInstant()))) {
            this.activeStatus = BoardActiveStatus.INACTIVE;
        }
    }
}
