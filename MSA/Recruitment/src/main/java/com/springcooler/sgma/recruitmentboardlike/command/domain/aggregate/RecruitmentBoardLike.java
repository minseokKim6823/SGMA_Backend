package com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="RECRUITMENT_BOARD_LIKE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@IdClass(RecruitmentBoardLikeId.class)
public class RecruitmentBoardLike {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "recruitment_board_id")
    private Long recruitmentBoardId;
}
