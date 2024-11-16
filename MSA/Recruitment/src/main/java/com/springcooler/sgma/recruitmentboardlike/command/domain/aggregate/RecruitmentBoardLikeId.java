package com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentBoardLikeId implements Serializable {
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "RECRUITMENT_BOARD_ID")
    private Long recruitmentBoardId;

}
