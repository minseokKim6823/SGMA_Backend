package com.springcooler.sgma.studygroupmember.query.service;

import com.springcooler.sgma.studygroupmember.common.exception.CommonException;
import com.springcooler.sgma.studygroupmember.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupmember.query.dto.StudyGroupMemberDTO;
import com.springcooler.sgma.studygroupmember.query.repository.StudyGroupMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupMemberServiceImpl implements StudyGroupMemberService {

    private final StudyGroupMemberMapper studyGroupMemberMapper;

    @Autowired
    public StudyGroupMemberServiceImpl(StudyGroupMemberMapper studyGroupMemberMapper) {
        this.studyGroupMemberMapper = studyGroupMemberMapper;
    }

    // 스터디 그룹원 단건 조회(그룹원 아이디)
    @Override
    public StudyGroupMemberDTO findStudyGroupMemberByMemberId(Long memberId) {
        StudyGroupMemberDTO member = studyGroupMemberMapper.findStudyGroupMemberByMemberId(memberId);
        if (member == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_MEMBER);
        }
        return member;
    }

    // 스터디 그룹원 그룹별 조회
    @Override
    public List<StudyGroupMemberDTO> findStudyGroupMembersByGroupId(Long groupId) {
        List<StudyGroupMemberDTO> members = studyGroupMemberMapper.findStudyGroupMembersByGroupId(groupId);
        if (members == null || members.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_MEMBER);
        }
        return members;
    }

    @Override
    public List<StudyGroupMemberDTO> findStudyGroupMembersByUserId(Long userId) {
        List<StudyGroupMemberDTO> members = studyGroupMemberMapper.findStudyGroupMembersByUserId(userId);
        if (members == null || members.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_MEMBER);
        }
        return members;
    }

}
