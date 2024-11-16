package com.springcooler.sgma.studygroupapplicant.command.application.service;


import com.springcooler.sgma.recruitmentboardlike.common.exception.CommonException;
import com.springcooler.sgma.recruitmentboardlike.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupapplicant.command.domain.aggregate.StudyGroupApplicantId;
import com.springcooler.sgma.studygroupapplicant.command.application.dto.StudyGroupApplicantCommandDTO;
import com.springcooler.sgma.studygroupapplicant.command.domain.aggregate.ApplicationStatus;
import com.springcooler.sgma.studygroupapplicant.command.domain.aggregate.StudyGroupApplicant;
import com.springcooler.sgma.studygroupapplicant.command.domain.aggregate.vo.RequestStudyGroupMemberVO;
import com.springcooler.sgma.studygroupapplicant.command.domain.repository.StudyGroupApplicantRepository;

import com.springcooler.sgma.studygroupapplicant.command.infrastructure.service.InfraStudyGroupApplicantService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudyGroupApplicantCommandServiceImpl implements StudyGroupApplicantCommandService{

    private final InfraStudyGroupApplicantService infraStudyGroupApplicantService;
    private final StudyGroupApplicantRepository studyGroupApplicantRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudyGroupApplicantCommandServiceImpl(InfraStudyGroupApplicantService infraStudyGroupApplicantService,
                                                 StudyGroupApplicantRepository studyGroupApplicantRepository,
                                                 ModelMapper modelMapper) {
        this.infraStudyGroupApplicantService = infraStudyGroupApplicantService;
        this.studyGroupApplicantRepository = studyGroupApplicantRepository;
        this.modelMapper = modelMapper;
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
            throw new CommonException(ErrorCode.NOT_FOUND_APPLICANT);
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
        RequestStudyGroupMemberVO newMember = new RequestStudyGroupMemberVO();
        newMember.setUserId(userId);
        newMember.setGroupId(inputGroupId);
        System.out.println(newMember);

        infraStudyGroupApplicantService.registAcceptedMember(newMember);
    }

    @Override
    @Transactional
    public void rejectStudyGroupApplicant(long userId, long recruitmentBoardId) {
        StudyGroupApplicantId studyGroupApplicantId = new StudyGroupApplicantId(userId, recruitmentBoardId);
        StudyGroupApplicant studyGroupApplicant = studyGroupApplicantRepository.findById(studyGroupApplicantId).get();
        studyGroupApplicant.setApplicationStatus(ApplicationStatus.REJECT);
    }
}
