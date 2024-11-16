package com.springcooler.sgma.studygroupmember.command.application.service;

import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;

public interface AppStudyGroupMemberService {

    // 스터디 그룹장 추가
    StudyGroupMemberDTO registStudyGroupOwner(StudyGroupMemberDTO owner);

    // 스터디 그룹원 추가
    StudyGroupMemberDTO registStudyGroupMember(StudyGroupMemberDTO newMember);

    // 스터디 그룹원 정보 수정
    StudyGroupMemberDTO modifyStudyGroupMember(StudyGroupMemberDTO modifyMember);

    // 스터디 그룹원 삭제
    void deleteStudyGroupMember(Long memberId);

}
