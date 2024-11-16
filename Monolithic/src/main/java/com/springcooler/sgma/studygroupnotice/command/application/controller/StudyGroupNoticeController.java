package com.springcooler.sgma.studygroupnotice.command.application.controller;

import com.springcooler.sgma.studygroupnotice.command.application.dto.StudyGroupNoticeDTO;
import com.springcooler.sgma.studygroupnotice.command.application.service.AppStudyGroupNoticeService;
import com.springcooler.sgma.studygroupnotice.command.domain.aggregate.vo.RequestStudyGroupNoticeVO;
import com.springcooler.sgma.studygroupnotice.command.domain.aggregate.vo.ResponseStudyGroupNoticeVO;
import com.springcooler.sgma.studygroupnotice.common.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandStudyGroupNoticeController")
@RequestMapping("/api/study-group/notices")
public class StudyGroupNoticeController {

    private final ModelMapper modelMapper;
    private final AppStudyGroupNoticeService studyGroupNoticeService;

    @Autowired
    public StudyGroupNoticeController(ModelMapper modelMapper,
                                      AppStudyGroupNoticeService studyGroupNoticeService) {
        this.modelMapper = modelMapper;
        this.studyGroupNoticeService = studyGroupNoticeService;
    }

    // 스터디그룹 공지사항 생성
    @PostMapping("/")
    public ResponseDTO<?> registStudyGroupNotice(@RequestBody RequestStudyGroupNoticeVO newNotice) {
        StudyGroupNoticeDTO notice = modelMapper.map(newNotice, StudyGroupNoticeDTO.class);
        notice = studyGroupNoticeService.registStudyGroupNotice(notice);
        ResponseStudyGroupNoticeVO res = modelMapper.map(notice, ResponseStudyGroupNoticeVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디그룹 공지사항 정보 수정
    @PutMapping("/")
    public ResponseDTO<?> modifyStudyGroupNotice(@RequestBody RequestStudyGroupNoticeVO modifyNotice) {
        StudyGroupNoticeDTO notice = modelMapper.map(modifyNotice, StudyGroupNoticeDTO.class);
        notice = studyGroupNoticeService.modifyStudyGroupNotice(notice);
        ResponseStudyGroupNoticeVO res = modelMapper.map(notice, ResponseStudyGroupNoticeVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디그룹 공지사항 삭제
    @DeleteMapping("/{noticeId}")
    public ResponseDTO<?> deleteStudyGroupNotice(@PathVariable Long noticeId) {
        studyGroupNoticeService.deleteStudyGroupNotice(noticeId);
        return ResponseDTO.ok(null);
    }

}
