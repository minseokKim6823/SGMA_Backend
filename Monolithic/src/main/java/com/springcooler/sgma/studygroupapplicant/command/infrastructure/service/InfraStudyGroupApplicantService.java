package com.springcooler.sgma.studygroupapplicant.command.infrastructure.service;

import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import org.springframework.transaction.annotation.Transactional;

public interface InfraStudyGroupApplicantService {

    @Transactional
    void approveStudyGroupApplicant(StudyGroupMemberDTO studyGroupMemberDTO);
}
