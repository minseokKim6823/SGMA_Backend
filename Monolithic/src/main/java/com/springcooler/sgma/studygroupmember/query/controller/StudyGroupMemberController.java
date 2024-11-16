package com.springcooler.sgma.studygroupmember.query.controller;

import com.springcooler.sgma.studygroupmember.common.ResponseDTO;
import com.springcooler.sgma.studygroupmember.query.dto.StudyGroupMemberDTO;
import com.springcooler.sgma.studygroupmember.query.service.StudyGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryStudyGroupMemberController")
@RequestMapping("/api/study-group/members")
public class StudyGroupMemberController {

    StudyGroupMemberService studyGroupMemberService;

    @Autowired
    public StudyGroupMemberController(StudyGroupMemberService studyGroupMemberService) {
        this.studyGroupMemberService = studyGroupMemberService;
    }

    // 스터디 그룹원 단건 조회(그룹원 아이디)
    @GetMapping("/{memberId}")
    public ResponseDTO<?> findStudyGroupMemberByMemberId(@PathVariable Long memberId) {
        StudyGroupMemberDTO member = studyGroupMemberService.findStudyGroupMemberByMemberId(memberId);
        return ResponseDTO.ok(member);
    }

    // 스터디 그룹원 그룹별 조회
    @GetMapping("/group-id/{groupId}")
    public ResponseDTO<?> findStudyGroupMembersByGroupId(@PathVariable Long groupId) {
        List<StudyGroupMemberDTO> members = studyGroupMemberService.findStudyGroupMembersByGroupId(groupId);
        return ResponseDTO.ok(members);
    }

    // 스터디 그룹원 회원별 조회
    @GetMapping("/user-id/{userId}")
    public ResponseDTO<?> findStudyGroupMembersByUserId(@PathVariable Long userId) {
        List<StudyGroupMemberDTO> members = studyGroupMemberService.findStudyGroupMembersByUserId(userId);
        return ResponseDTO.ok(members);
    }

}
