package com.springcooler.sgma.studygroupboardlike.command.domain.aggregate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudyGroupBoardLikePK implements Serializable {

    @Column(name="STUDY_GROUP_BOARD_ID")
    private Long studyGroupBoardId;

    @Column(name="MEMBER_ID")
    private Long memberId;

}
