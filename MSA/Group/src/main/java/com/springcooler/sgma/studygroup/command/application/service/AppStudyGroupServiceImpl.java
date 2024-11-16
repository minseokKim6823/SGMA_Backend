package com.springcooler.sgma.studygroup.command.application.service;

import com.springcooler.sgma.studygroup.command.application.dto.StudyGroupDTO;
import com.springcooler.sgma.studygroup.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroup.command.domain.aggregate.StudyGroup;
import com.springcooler.sgma.studygroup.command.domain.aggregate.StudyGroupStatus;
import com.springcooler.sgma.studygroup.command.domain.repository.StudyGroupRepository;
import com.springcooler.sgma.studygroup.command.domain.service.DomainStudyGroupService;
import com.springcooler.sgma.studygroup.command.infrastructure.service.InfraStudyGroupService;
import com.springcooler.sgma.studygroup.common.exception.CommonException;
import com.springcooler.sgma.studygroup.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppStudyGroupServiceImpl implements AppStudyGroupService {

    private final ModelMapper modelMapper;
    private final DomainStudyGroupService domainStudyGroupService;
    private final InfraStudyGroupService infraStudyGroupService;
    private final StudyGroupRepository studyGroupRepository;

    @Autowired
    public AppStudyGroupServiceImpl(ModelMapper modelMapper,
                                    DomainStudyGroupService domainStudyGroupService,
                                    InfraStudyGroupService infraStudyGroupService,
                                    StudyGroupRepository studyGroupRepository) {
        this.modelMapper = modelMapper;
        this.domainStudyGroupService = domainStudyGroupService;
        this.infraStudyGroupService = infraStudyGroupService;
        this.studyGroupRepository = studyGroupRepository;
    }

    // 스터디 그룹 생성
    @Transactional
    @Override
    public StudyGroupDTO registStudyGroup(StudyGroupDTO newStudyGroup) {
        // DTO 유효성 검사
        if(!domainStudyGroupService.isValidDTO(RestStatus.POST, newStudyGroup))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 그룹명 중복 검사
        StudyGroup existingGroupName =
                studyGroupRepository.findByGroupName(newStudyGroup.getGroupName());

        if(existingGroupName != null)
            throw new CommonException(ErrorCode.DUPLICATE_GROUP_NAME_EXISTS);

        // 스터디 그룹 생성 코드
        StudyGroupDTO tempStudyGroup = StudyGroupDTO.builder()
                .groupName(newStudyGroup.getGroupName())
                .activeStatus(StudyGroupStatus.ACTIVE)
                .groupMembers(0)
                .userId(newStudyGroup.getUserId())
                .studyGroupCategoryId(newStudyGroup.getStudyGroupCategoryId())
                .build();

        StudyGroup studyGroup =
                studyGroupRepository.save(modelMapper.map(tempStudyGroup, StudyGroup.class));

        // 스터디 그룹장 추가 요청 코드
        StudyGroupMemberDTO owner = StudyGroupMemberDTO.builder()
                .userId(studyGroup.getUserId())
                .groupId(studyGroup.getGroupId())
                .build();
        infraStudyGroupService.registStudyGroupOwner(owner);

        // 스터디 그룹원 수 1명으로 초기화
        studyGroup.setGroupMembers(1);
        studyGroupRepository.save(studyGroup);

        return modelMapper.map(studyGroup, StudyGroupDTO.class);
    }

    // 스터디 그룹장 신청 승인
    @Transactional
    @Override
    public StudyGroupDTO registAcceptedMember(StudyGroupMemberDTO newMember) {
        // 스터디 그룹 조회
        StudyGroup existingStudyGroup = studyGroupRepository.findById(newMember.getGroupId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP));

        // 스터디 그룹원 추가 요청
        infraStudyGroupService.registStudyGroupMember(newMember);

        // 스터디 그룹원 수 + 1
        existingStudyGroup.setGroupMembers(existingStudyGroup.getGroupMembers() + 1);
        studyGroupRepository.save(existingStudyGroup);

        return modelMapper.map(existingStudyGroup, StudyGroupDTO.class);
    }

    // 스터디 그룹 정보 수정
    @Transactional
    @Override
    public StudyGroupDTO modifyStudyGroup(StudyGroupDTO modifyStudyGroup) {
        // DTO 유효성 검사
        if(!domainStudyGroupService.isValidDTO(RestStatus.PUT, modifyStudyGroup))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 기존 엔티티 조회
        StudyGroup existingStudyGroup = studyGroupRepository.findById(modifyStudyGroup.getGroupId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP));

        // 그룹명 중복 검사
        StudyGroup existingGroupName =
                studyGroupRepository.findByGroupName(modifyStudyGroup.getGroupName());

        if(existingGroupName != null)
            throw new CommonException(ErrorCode.DUPLICATE_GROUP_NAME_EXISTS);

        // 변경된 정보 매핑
        existingStudyGroup.setGroupName(modifyStudyGroup.getGroupName());
        existingStudyGroup.setStudyGroupCategoryId(modifyStudyGroup.getStudyGroupCategoryId());

        // 엔티티 저장
        studyGroupRepository.save(existingStudyGroup);

        return modelMapper.map(existingStudyGroup, StudyGroupDTO.class);
    }

    // 스터디 그룹 이름 수정
    @Transactional
    @Override
    public StudyGroupDTO modifyStudyGroupName(StudyGroupDTO modifyStudyGroup) {
        // DTO 유효성 검사
        if(!domainStudyGroupService.isValidDTO(RestStatus.PATCH, modifyStudyGroup))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 기존 엔티티 조회
        StudyGroup existingStudyGroup = studyGroupRepository.findById(modifyStudyGroup.getGroupId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP));

        // 그룹명 중복 검사
        StudyGroup existingGroupName =
                studyGroupRepository.findByGroupName(modifyStudyGroup.getGroupName());

        if(existingGroupName != null)
            throw new CommonException(ErrorCode.DUPLICATE_GROUP_NAME_EXISTS);

        // 변경된 이름 매핑
        existingStudyGroup.setGroupName(modifyStudyGroup.getGroupName());

        // 엔티티 저장
        studyGroupRepository.save(existingStudyGroup);

        return modelMapper.map(existingStudyGroup, StudyGroupDTO.class);
    }

    // 스터디 그룹 카테고리 수정
    @Transactional
    @Override
    public StudyGroupDTO modifyStudyGroupCategory(StudyGroupDTO modifyStudyGroup) {
        // DTO 유효성 검사
        if(!domainStudyGroupService.isValidDTO(RestStatus.PATCH, modifyStudyGroup))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 기존 엔티티 조회
        StudyGroup existingStudyGroup = studyGroupRepository.findById(modifyStudyGroup.getGroupId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP));

        // 변경된 카테고리 매핑
        existingStudyGroup.setStudyGroupCategoryId(modifyStudyGroup.getStudyGroupCategoryId());

        // 엔티티 저장
        studyGroupRepository.save(existingStudyGroup);

        return modelMapper.map(existingStudyGroup, StudyGroupDTO.class);
    }

    // 스터디 그룹원 탈퇴
    @Transactional
    @Override
    public StudyGroupDTO deleteQuitMember(Long memberId, Long groupId) {
        // 스터디 그룹 조회
        StudyGroup existingStudyGroup = studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP));

        // 스터디 그룹원 삭제 요청
        infraStudyGroupService.deleteStudyGroupMember(memberId);

        // 스터디 그룹원 수 - 1
        existingStudyGroup.setGroupMembers(existingStudyGroup.getGroupMembers() - 1);
        studyGroupRepository.save(existingStudyGroup);

        return modelMapper.map(existingStudyGroup, StudyGroupDTO.class);
    }

    // 스터디 그룹 삭제
    @Transactional
    @Override
    public void deleteStudyGroup(Long groupId) {
        // 스터디 그룹 조회
        StudyGroup deleteStudyGroup = studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP));

        // 유효성 검사
        if(!domainStudyGroupService.isActive(deleteStudyGroup.getActiveStatus()))
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP);

        // INACTIVE 처리
        deleteStudyGroup.setActiveStatus(StudyGroupStatus.INACTIVE);
        studyGroupRepository.save(deleteStudyGroup);
    }
}
