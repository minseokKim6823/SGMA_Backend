package com.springcooler.sgma.studygroupmember.query.repository;

import com.springcooler.sgma.studygroupmember.query.dto.StudyGroupMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyGroupMemberMapper {

    // 스터디 그룹원 단건 조회(그룹원 아이디)
    StudyGroupMemberDTO findStudyGroupMemberByMemberId(long memberId);

    // 스터디 그룹원 그룹별 조회
    List<StudyGroupMemberDTO> findStudyGroupMembersByGroupId(long groupId);

    // 스터디 그룹원 회원별 조회
    List<StudyGroupMemberDTO> findStudyGroupMembersByUserId(long userId);

}
