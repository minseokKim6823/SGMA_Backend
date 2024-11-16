package com.springcooler.sgma.studygroupapplicant.command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyGroupApplicantId implements Serializable {
    private Long userId;
    private Long recruitmentBoardId;
}
