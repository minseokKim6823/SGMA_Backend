package com.springcooler.sgma.studygroupboardlike.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="STUDY_GROUP_BOARD_LIKE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@IdClass(StudyGroupBoardLikePK.class)
public class StudyGroupBoardLike {

    @Id
    @Column(name="STUDY_GROUP_BOARD_ID")
    private Long studyGroupBoardId;

    @Id
    @Column(name="MEMBER_ID")
    private Long memberId;

}
