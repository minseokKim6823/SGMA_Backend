package com.springcooler.sgma.studygroupnotice.query.controller;

import com.springcooler.sgma.studygroupnotice.common.ResponseDTO;
import com.springcooler.sgma.studygroupnotice.query.dto.PageDTO;
import com.springcooler.sgma.studygroupnotice.query.dto.StudyGroupNoticeDTO;
import com.springcooler.sgma.studygroupnotice.query.service.StudyGroupNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDTO<?> findStudyGroupNoticesByGroupId(@PathVariable("groupId") Long groupId,
                                                         @RequestParam("page") Integer pageNo) {
        PageDTO<StudyGroupNoticeDTO> page =
                studyGroupNoticeService.findStudyGroupNoticesByGroupId(groupId, pageNo);
        return ResponseDTO.ok(page);
    }

    // 스터디그룹 공지사항 단건 조회(공지사항 아이디)
    @GetMapping("/{noticeId}")
    public ResponseDTO<?> findStudyGroupNoticeByNoticeId(@PathVariable("noticeId") Long noticeId) {
        StudyGroupNoticeDTO notice =
                studyGroupNoticeService.findStudyGroupNoticeByNoticeId(noticeId);
        return ResponseDTO.ok(notice);
    }

    // 스터디그룹 공지사항 제목으로 조회
    @GetMapping("/group-id/{groupId}/title/{title}")
    public ResponseDTO<?> findStudyGroupNoticesByTitle(@PathVariable("groupId") Long groupId,
                                                       @PathVariable("title") String title,
                                                       @RequestParam("page") Integer pageNo) {
        PageDTO<StudyGroupNoticeDTO> page =
                studyGroupNoticeService.findStudyGroupNoticesByTitle(groupId, title, pageNo);
        return ResponseDTO.ok(page);
    }

    // 스터디그룹 공지사항 내용으로 조회
    @GetMapping("/group-id/{groupId}/content/{content}")
    public ResponseDTO<?> findStudyGroupNoticesByContent(@PathVariable("groupId") Long groupId,
                                                         @PathVariable("content") String content,
                                                         @RequestParam("page") Integer pageNo) {
        PageDTO<StudyGroupNoticeDTO> page =
                studyGroupNoticeService.findStudyGroupNoticesByContent(groupId, content, pageNo);
        return ResponseDTO.ok(page);
    }

}
