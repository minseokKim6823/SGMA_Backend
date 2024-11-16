package com.springcooler.sgma.studygroup.query.service;

import com.springcooler.sgma.studygroup.query.dto.StudyGroupDTO;

import java.util.List;

public interface StudyGroupService {

    // 스터디 그룹 전체 조회
    List<StudyGroupDTO> findAllStudyGroups();

    // 생성한 스터디 그룹 조회
    List<StudyGroupDTO> findStudyGroupsByOwnerId(Long ownerId);

    // 참가한 스터디 그룹 조회
    List<StudyGroupDTO> findStudyGroupsByParticipantId(Long participantId);

    // 스터디 그룹 카테고리별 조회
    List<StudyGroupDTO> findStudyGroupsByCategoryId(Integer categoryId);

    // 스터디 그룹 단건 조회(그룹 아이디)
    StudyGroupDTO findStudyGroupByGroupId(Long groupId);

    // 스터디 그룹 단건 조회(그룹 이름)
    StudyGroupDTO findStudyGroupByGroupName(String groupName);

}
