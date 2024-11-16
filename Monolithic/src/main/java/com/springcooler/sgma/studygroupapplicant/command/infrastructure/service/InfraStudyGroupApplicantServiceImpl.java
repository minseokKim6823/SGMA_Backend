package com.springcooler.sgma.studygroupapplicant.command.infrastructure.service;

import com.springcooler.sgma.studygroup.command.application.service.AppStudyGroupService;
import com.springcooler.sgma.studygroupapplicant.command.domain.repository.StudyGroupApplicantRepository;
import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfraStudyGroupApplicantServiceImpl implements InfraStudyGroupApplicantService{


    private AppStudyGroupService appStudyGroupService;

    @Autowired
    public InfraStudyGroupApplicantServiceImpl(StudyGroupApplicantRepository studyGroupApplicantRepository, AppStudyGroupService appStudyGroupService) {
        this.appStudyGroupService = appStudyGroupService;
    }

    @Override
    public void approveStudyGroupApplicant(StudyGroupMemberDTO studyGroupMemberDTO) {
        appStudyGroupService.registAcceptedMember(studyGroupMemberDTO);
    }
}
