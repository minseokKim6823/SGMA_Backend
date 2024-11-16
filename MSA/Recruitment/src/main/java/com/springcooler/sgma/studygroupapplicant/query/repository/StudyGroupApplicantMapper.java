package com.springcooler.sgma.studygroupapplicant.query.repository;


import com.springcooler.sgma.studygroupapplicant.query.dto.StudyGroupApplicantDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyGroupApplicantMapper {

    List<StudyGroupApplicantDTO> findStudyGroupApplicantByUserId(Long userId);

    List<StudyGroupApplicantDTO> findStudyGroupApplicantByRecruitmentBoardId(Long recruitmentBoardId);

    List<StudyGroupApplicantDTO> findStudyGroupApplicantByGroupId(Long groupId);
}
