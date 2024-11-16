package com.springcooler.sgma.studygroup.command.infrastructure.service;

import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import com.springcooler.sgma.studygroupmember.command.application.service.AppStudyGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InfraStudyGroupServiceImpl implements InfraStudyGroupService {

    private final AppStudyGroupMemberService studyGroupMemberService;

    @Autowired
    public InfraStudyGroupServiceImpl(AppStudyGroupMemberService studyGroupMemberService) {
        this.studyGroupMemberService = studyGroupMemberService;
    }

    // 스터디 그룹장 추가
    @Override
    public void registStudyGroupOwner(StudyGroupMemberDTO owner) {
        studyGroupMemberService.registStudyGroupOwner(owner);
    }

    // 스터디 그룹원 추가
    @Transactional
    @Override
    public void registStudyGroupMember(StudyGroupMemberDTO newMember) {
        studyGroupMemberService.registStudyGroupMember(newMember);
    }

    // 스터디 그룹원 삭제
    @Transactional
    @Override
    public void deleteStudyGroupMember(Long memberId) {
        studyGroupMemberService.deleteStudyGroupMember(memberId);
    }

}
