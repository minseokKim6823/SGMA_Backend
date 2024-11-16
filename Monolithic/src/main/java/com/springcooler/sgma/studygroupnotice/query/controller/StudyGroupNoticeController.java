package com.springcooler.sgma.studygroupnotice.query.controller;

import com.springcooler.sgma.studygroupnotice.common.ResponseDTO;
import com.springcooler.sgma.studygroupnotice.query.dto.StudyGroupNoticeDTO;
import com.springcooler.sgma.studygroupnotice.query.service.StudyGroupNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryStudyGroupNoticeController")
@RequestMapping("/api/study-group/notices")
public class StudyGroupNoticeController {

    private final StudyGroupNoticeService studyGroupNoticeService;

    @Autowired
    public StudyGroupNoticeController(StudyGroupNoticeService studyGroupNoticeService) {
        this.studyGroupNoticeService = studyGroupNoticeService;
    }

    // 스터디그룹 공지사항 전체 조회(스터디그룹 아이디)
    @GetMapping("/group-id/{groupId}")
    public ResponseDTO<?> findStudyGroupNoticesByGroupId(@PathVariable Long groupId) {
        List<StudyGroupNoticeDTO> notices = studyGroupNoticeService.findStudyGroupNoticesByGroupId(groupId);
        return ResponseDTO.ok(notices);
    }

    // 스터디그룹 공지사항 단건 조회(공지사항 아이디)
    @GetMapping("/{noticeId}")
    public ResponseDTO<?> findStudyGroupNoticeByNoticeId(@PathVariable Long noticeId) {
        StudyGroupNoticeDTO notice = studyGroupNoticeService.findStudyGroupNoticeByNoticeId(noticeId);
        return ResponseDTO.ok(notice);
    }

}
