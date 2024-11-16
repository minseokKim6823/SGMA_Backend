package com.springcooler.sgma.studygroupapplicant.query.repository;


import com.springcooler.sgma.studygroupapplicant.query.dto.StudyGroupApplicantDTO;

import java.util.List;

public interface StudyGroupApplicantMapper {

    List<StudyGroupApplicantDTO> studyGroupApplicant1(Long userId);

    List<StudyGroupApplicantDTO> studyGroupApplicant2(Long recruitBoardId);
}
