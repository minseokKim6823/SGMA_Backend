package com.springcooler.sgma.studygroup.command.infrastructure.service;

import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;

public interface InfraStudyGroupService {

    // 스터디 그룹장 추가
    void registStudyGroupOwner(StudyGroupMemberDTO owner);

    // 스터디 그룹원 추가
    void registStudyGroupMember(StudyGroupMemberDTO newMember);

    // 스터디 그룹원 삭제
    void deleteStudyGroupMember(Long memberId);

}
