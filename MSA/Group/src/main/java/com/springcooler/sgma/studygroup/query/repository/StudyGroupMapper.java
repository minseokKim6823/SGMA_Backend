package com.springcooler.sgma.studygroup.query.repository;

import com.springcooler.sgma.studygroup.query.dto.MyStudyGroupDTO;
import com.springcooler.sgma.studygroup.query.dto.StudyGroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyGroupMapper {

    // 스터디그룹 전체 조회
    List<StudyGroupDTO> findAllStudyGroups();

    // 그룹장인 스터디그룹 조회
    List<StudyGroupDTO> findStudyGroupsByOwnerId(Long ownerId);

    // 그룹원인 스터디그룹 조회
    List<StudyGroupDTO> findStudyGroupsByParticipantId(Long participantId);

    // 스터디그룹 카테고리별 조회
    List<StudyGroupDTO> findStudyGroupsByCategoryId(Integer categoryId);

    // 스터디그룹 단건 조회(그룹 아이디)
    StudyGroupDTO findStudyGroupByGroupId(Long groupId);

    // 스터디그룹 단건 조회(그룹 이름)
    StudyGroupDTO findStudyGroupByGroupName(String groupName);

    // 내가 가입한 스터디 그룹 전체 조회
    List<MyStudyGroupDTO> findStudyGroupsByUserId(Long userId);

}
