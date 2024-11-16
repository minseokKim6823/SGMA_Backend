package com.springcooler.sgma.studygroupmember.query.service;

import com.springcooler.sgma.studygroupmember.query.dto.StudyGroupMemberDTO;

import java.util.List;

public interface StudyGroupMemberService {

    // 스터디 그룹원 단건 조회(그룹원 아이디)
    StudyGroupMemberDTO findStudyGroupMemberByMemberId(Long memberId);

    // 스터디 그룹원 그룹별 조회
    List<StudyGroupMemberDTO> findStudyGroupMembersByGroupId(Long groupId);

    // 스터디 그룹원 회원별 조회
    List<StudyGroupMemberDTO> findStudyGroupMembersByUserId(Long userId);

}
