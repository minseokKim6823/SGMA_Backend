package com.springcooler.sgma.studygroup.query.service;

import com.springcooler.sgma.studygroup.query.dto.StudyGroupDTO;
import com.springcooler.sgma.studygroup.query.repository.StudyGroupMapper;
import com.springcooler.sgma.studygroup.common.exception.CommonException;
import com.springcooler.sgma.studygroup.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupServiceImpl implements StudyGroupService {

    private final StudyGroupMapper studyGroupMapper;

    @Autowired
    public StudyGroupServiceImpl(StudyGroupMapper studyGroupMapper) {
        this.studyGroupMapper = studyGroupMapper;
    }

    // 스터디 그룹 전체 조회
    @Override
    public List<StudyGroupDTO> findAllStudyGroups() {
        List<StudyGroupDTO> studyGroups = studyGroupMapper.findAllStudyGroups();
        if(studyGroups == null || studyGroups.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP);
        }
        return studyGroups;
    }

    // 그룹장인 스터디 그룹 조회
    @Override
    public List<StudyGroupDTO> findStudyGroupsByOwnerId(Long ownerId) {
        List<StudyGroupDTO> studyGroups = studyGroupMapper.findStudyGroupsByOwnerId(ownerId);
        if(studyGroups == null || studyGroups.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP);
        }
        return studyGroups;
    }

    // 그룹원인 스터디 그룹 조회
    @Override
    public List<StudyGroupDTO> findStudyGroupsByParticipantId(Long participantId) {
        List<StudyGroupDTO> studyGroups = studyGroupMapper.findStudyGroupsByParticipantId(participantId);
        if(studyGroups == null || studyGroups.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP);
        }
        return studyGroups;
    }

    // 스터디 그룹 카테고리별 조회
    @Override
    public List<StudyGroupDTO> findStudyGroupsByCategoryId(Integer categoryId) {
        List<StudyGroupDTO> studyGroups = studyGroupMapper.findStudyGroupsByCategoryId(categoryId);
        if(studyGroups == null || studyGroups.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP);
        }
        return studyGroups;
    }

    // 스터디 그룹 단건 조회(그룹 아이디)
    @Override
    public StudyGroupDTO findStudyGroupByGroupId(Long groupId) {
        StudyGroupDTO studyGroup = studyGroupMapper.findStudyGroupByGroupId(groupId);
        if(studyGroup == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP);
        }
        return studyGroup;
    }

    // 스터디 그룹 단건 조회(그룹 이름)
    @Override
    public StudyGroupDTO findStudyGroupByGroupName(String groupName) {
        StudyGroupDTO studyGroup = studyGroupMapper.findStudyGroupByGroupName(groupName);
        if(studyGroup == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP);
        }
        return studyGroup;
    }

}
