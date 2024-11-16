package com.springcooler.sgma.studygroup.command.application.service;

import com.springcooler.sgma.studygroup.command.application.dto.StudyGroupDTO;
import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;

public interface AppStudyGroupService {

    // 스터디 그룹 생성
    StudyGroupDTO registStudyGroup(StudyGroupDTO newStudyGroup);

    // 스터디 그룹장 신청 승인
    StudyGroupDTO registAcceptedMember(StudyGroupMemberDTO newMember);

    // 스터디 그룹 정보 수정
    StudyGroupDTO modifyStudyGroup(StudyGroupDTO modifyStudyGroup);

    // 스터디 그룹 이름 수정
    StudyGroupDTO modifyStudyGroupName(StudyGroupDTO modifyStudyGroup);

    // 스터디 그룹 카테고리 수정
    StudyGroupDTO modifyStudyGroupCategory(StudyGroupDTO modifyStudyGroup);

    // 스터디 그룹원 탈퇴
    StudyGroupDTO deleteQuitMember(long memberId, long groupId);

    // 스터디 그룹 삭제
    void deleteStudyGroup(long groupId);

}
