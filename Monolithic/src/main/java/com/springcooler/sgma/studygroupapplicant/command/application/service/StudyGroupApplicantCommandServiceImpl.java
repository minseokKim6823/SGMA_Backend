package com.springcooler.sgma.studygroupapplicant.command.application.service;


import com.springcooler.sgma.recruitmentboardcomment.command.application.dto.RecruitmentBoardCommentCommandDTO;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.ActiveStatus;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.RecruitmentBoardComment;
import com.springcooler.sgma.studygroupapplicant.command.domain.aggregate.StudyGroupApplicantId;
import com.springcooler.sgma.studygroup.command.application.service.AppStudyGroupService;
import com.springcooler.sgma.studygroupapplicant.command.application.dto.StudyGroupApplicantCommandDTO;
import com.springcooler.sgma.studygroupapplicant.command.domain.aggregate.ApplicationStatus;
import com.springcooler.sgma.studygroupapplicant.command.domain.aggregate.StudyGroupApplicant;
import com.springcooler.sgma.studygroupapplicant.command.domain.repository.StudyGroupApplicantRepository;

import com.springcooler.sgma.studygroupapplicant.command.infrastructure.service.InfraStudyGroupApplicantService;
import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class StudyGroupApplicantCommandServiceImpl implements StudyGroupApplicantCommandService{

    private final InfraStudyGroupApplicantService infraStudyGroupApplicantService;
    private final StudyGroupApplicantRepository studyGroupApplicantRepository;
    private final ModelMapper modelMapper;
    private final AppStudyGroupService appStudyGroupService;

    @Autowired
    public StudyGroupApplicantCommandServiceImpl(InfraStudyGroupApplicantService infraStudyGroupApplicantService, StudyGroupApplicantRepository studyGroupApplicantRepository, ModelMapper modelMapper, AppStudyGroupService appStudyGroupService) {
        this.infraStudyGroupApplicantService = infraStudyGroupApplicantService;
        this.studyGroupApplicantRepository = studyGroupApplicantRepository;
        this.modelMapper = modelMapper;
        this.appStudyGroupService = appStudyGroupService;
    }

    @Override
    @Transactional
    public StudyGroupApplicant applyStudyGroup(StudyGroupApplicantCommandDTO studyGroupApplicantCommandDTO) {
        StudyGroupApplicant studyGroupApplicant = StudyGroupApplicant.builder()
                .userId(studyGroupApplicantCommandDTO.getUserId())
                .applicationStatus(ApplicationStatus.WAIT)
                .recruitmentBoardId(studyGroupApplicantCommandDTO.getRecruitmentBoardId())
                .groupId(studyGroupApplicantCommandDTO.getGroupId())
                .build();
        studyGroupApplicantRepository.save(studyGroupApplicant);

        return studyGroupApplicant;
    }

    @Override
    @Transactional
    public void cancelStudyGroupApply(long userId, long recruitmentBoardId) {
        StudyGroupApplicantId studyGroupApplicantId = new StudyGroupApplicantId(userId, recruitmentBoardId);
        if (!studyGroupApplicantRepository.existsById(studyGroupApplicantId)) {
            throw new EntityNotFoundException("신청자를 찾을 수 없습니다.");
        }
        studyGroupApplicantRepository.deleteById(studyGroupApplicantId);
    }



    @Override
    @Transactional
    public void approveStudyGroupApplicant(long userId, long recruitmentBoardId) {
        StudyGroupApplicantId studyGroupApplicantId = new StudyGroupApplicantId(userId, recruitmentBoardId);

        // 복합 키를 사용해 검색
        StudyGroupApplicant studyGroupApplicant = studyGroupApplicantRepository.findById(studyGroupApplicantId).get();
        Long inputGroupId = studyGroupApplicant.getGroupId();

        // 상태 업데이트
        studyGroupApplicant.setApplicationStatus(ApplicationStatus.ACCEPT);
        studyGroupApplicantRepository.save(studyGroupApplicant);

        // DTO 생성 및 서비스 호출
        StudyGroupMemberDTO studyGroupMemberDTO = new StudyGroupMemberDTO();
        studyGroupMemberDTO.setUserId(userId);
        studyGroupMemberDTO.setGroupId(inputGroupId);

        infraStudyGroupApplicantService.approveStudyGroupApplicant(studyGroupMemberDTO);
    }

    @Override
    @Transactional
    public void rejectStudyGroupApplicant(long userId, long recruitmentBoardId) {
        StudyGroupApplicantId studyGroupApplicantId = new StudyGroupApplicantId(userId, recruitmentBoardId);
        StudyGroupApplicant studyGroupApplicant = studyGroupApplicantRepository.findById(studyGroupApplicantId).get();
        studyGroupApplicant.setApplicationStatus(ApplicationStatus.REJECT);
    }
}
