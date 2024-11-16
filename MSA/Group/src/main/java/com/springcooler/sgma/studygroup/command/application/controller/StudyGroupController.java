package com.springcooler.sgma.studygroup.command.application.controller;

import com.springcooler.sgma.studygroup.command.application.dto.StudyGroupDTO;
import com.springcooler.sgma.studygroup.command.application.service.AppStudyGroupService;
import com.springcooler.sgma.studygroup.command.domain.aggregate.vo.RequestStudyGroupVO;
import com.springcooler.sgma.studygroup.command.domain.aggregate.vo.ResponseStudyGroupVO;
import com.springcooler.sgma.studygroup.common.ResponseDTO;
import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.vo.RequestStudyGroupMemberVO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandStudyGroupController")
@RequestMapping("/api/study-groups")
@Slf4j
public class StudyGroupController {

    private final ModelMapper modelMapper;
    private final AppStudyGroupService studyGroupService;

    @Autowired
    public StudyGroupController(ModelMapper modelMapper,
                                AppStudyGroupService studyGroupService) {
        this.modelMapper = modelMapper;
        this.studyGroupService = studyGroupService;
    }

    // 스터디 그룹 생성
    @PostMapping
    public ResponseDTO<?> registStudyGroup(@RequestBody RequestStudyGroupVO newStudyGroup) {
        StudyGroupDTO studyGroup = modelMapper.map(newStudyGroup, StudyGroupDTO.class);
        studyGroup = studyGroupService.registStudyGroup(studyGroup);
        ResponseStudyGroupVO res = modelMapper.map(studyGroup, ResponseStudyGroupVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디 그룹장 신청 승인
    @PostMapping("/member")
    public ResponseDTO<?> registAcceptedMember(@RequestBody RequestStudyGroupMemberVO newMember) {
        StudyGroupMemberDTO studyGroupMemberDTO = modelMapper.map(newMember, StudyGroupMemberDTO.class);
        StudyGroupDTO studyGroup = studyGroupService.registAcceptedMember(studyGroupMemberDTO);
        ResponseStudyGroupVO res = modelMapper.map(studyGroup, ResponseStudyGroupVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디 그룹 정보 수정
    @PutMapping
    public ResponseDTO<?> modifyStudyGroup(@RequestBody RequestStudyGroupVO modifyStudyGroup) {
        StudyGroupDTO studyGroup = modelMapper.map(modifyStudyGroup, StudyGroupDTO.class);
        studyGroup = studyGroupService.modifyStudyGroup(studyGroup);
        ResponseStudyGroupVO res = modelMapper.map(studyGroup, ResponseStudyGroupVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디 그룹 이름 수정
    @PatchMapping("/group-name")
    public ResponseDTO<?> modifyStudyGroupName(@RequestBody RequestStudyGroupVO modifyStudyGroup) {
        StudyGroupDTO studyGroup = modelMapper.map(modifyStudyGroup, StudyGroupDTO.class);
        studyGroup = studyGroupService.modifyStudyGroupName(studyGroup);
        ResponseStudyGroupVO res = modelMapper.map(studyGroup, ResponseStudyGroupVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디 그룹 카테고리 수정
    @PatchMapping("/category")
    public ResponseDTO<?> modifyStudyGroupCategory(@RequestBody RequestStudyGroupVO modifyStudyGroup) {
        StudyGroupDTO studyGroup = modelMapper.map(modifyStudyGroup, StudyGroupDTO.class);
        studyGroup = studyGroupService.modifyStudyGroupCategory(studyGroup);
        ResponseStudyGroupVO res = modelMapper.map(studyGroup, ResponseStudyGroupVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디 그룹원 탈퇴
    @DeleteMapping("/member")
    public ResponseDTO<?> deleteQuitMember(@RequestParam("member-id") Long memberId,
                                              @RequestParam("group-id") Long groupId) {
        StudyGroupDTO studyGroup = studyGroupService.deleteQuitMember(memberId, groupId);
        ResponseStudyGroupVO res = modelMapper.map(studyGroup, ResponseStudyGroupVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디 그룹 삭제
    @DeleteMapping("/{groupId}")
    public ResponseDTO<?> deleteStudyGroup(@PathVariable("groupId") Long groupId) {
        studyGroupService.deleteStudyGroup(groupId);
        return ResponseDTO.ok(null);
    }

}
