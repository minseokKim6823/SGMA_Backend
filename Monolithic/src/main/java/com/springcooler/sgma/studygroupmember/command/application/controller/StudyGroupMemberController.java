package com.springcooler.sgma.studygroupmember.command.application.controller;

import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import com.springcooler.sgma.studygroupmember.command.application.service.AppStudyGroupMemberService;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.vo.RequestStudyGroupMemberVO;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.vo.ResponseStudyGroupMemberVO;
import com.springcooler.sgma.studygroupmember.common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandStudyGroupMemberController")
@RequestMapping("/api/study-group/members")
@Slf4j
public class StudyGroupMemberController {

    private final ModelMapper modelMapper;
    private final AppStudyGroupMemberService studyGroupMemberService;

    @Autowired
    public StudyGroupMemberController(ModelMapper modelMapper,
                                      AppStudyGroupMemberService studyGroupMemberService) {
        this.modelMapper = modelMapper;
        this.studyGroupMemberService = studyGroupMemberService;
    }

    @PostMapping("/")
    public ResponseDTO<?> registStudyGroupMember(@RequestBody RequestStudyGroupMemberVO newMember) {
        StudyGroupMemberDTO member = modelMapper.map(newMember, StudyGroupMemberDTO.class);
        member = studyGroupMemberService.registStudyGroupMember(member);
        ResponseStudyGroupMemberVO res = modelMapper.map(member, ResponseStudyGroupMemberVO.class);
        return ResponseDTO.ok(res);
    }

    @PutMapping("/")
    public ResponseDTO<?> modifyStudyGroupMember(@RequestBody RequestStudyGroupMemberVO modifyMember) {
        StudyGroupMemberDTO member = modelMapper.map(modifyMember, StudyGroupMemberDTO.class);
        member = studyGroupMemberService.modifyStudyGroupMember(member);
        ResponseStudyGroupMemberVO res = modelMapper.map(member, ResponseStudyGroupMemberVO.class);
        return ResponseDTO.ok(res);
    }

    @DeleteMapping("/{memberId}")
    public ResponseDTO<?> deleteStudyGroupMember(@PathVariable Long memberId) {
        studyGroupMemberService.deleteStudyGroupMember(memberId);
        return ResponseDTO.ok(null);
    }

}
