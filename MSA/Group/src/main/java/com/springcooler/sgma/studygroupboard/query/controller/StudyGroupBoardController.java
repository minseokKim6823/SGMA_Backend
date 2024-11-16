package com.springcooler.sgma.studygroupboard.query.controller;

import com.springcooler.sgma.studygroupboard.common.ResponseDTO;
import com.springcooler.sgma.studygroupboard.query.dto.PageDTO;
import com.springcooler.sgma.studygroupboard.query.dto.StudyGroupBoardDTO;
import com.springcooler.sgma.studygroupboard.query.service.StudyGroupBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("queryStudyGroupBoardController")
@RequestMapping("/api/study-group/boards")
public class StudyGroupBoardController {

    private final StudyGroupBoardService studyGroupBoardService;

    @Autowired
    public StudyGroupBoardController(StudyGroupBoardService studyGroupBoardService) {
        this.studyGroupBoardService = studyGroupBoardService;
    }

    // 게시글 그룹별 조회
    @GetMapping("/group-id/{groupId}")
    public ResponseDTO<?> findStudyGroupBoardsByGroupId(@PathVariable("groupId") Long groupId,
                                                        @RequestParam("page") Integer pageNo) {
        PageDTO<StudyGroupBoardDTO> page =
                studyGroupBoardService.findStudyGroupBoardsByGroupId(groupId, pageNo);
        return ResponseDTO.ok(page);
    }

    // 게시글 그룹원별 조회
    @GetMapping("/member-id/{memberId}")
    public ResponseDTO<?> findStudyGroupBoardsByMemberId(@PathVariable("memberId") Long memberId,
                                                         @RequestParam("page") Integer pageNo) {
        PageDTO<StudyGroupBoardDTO> page =
                studyGroupBoardService.findStudyGroupBoardsByMemberId(memberId, pageNo);
        return ResponseDTO.ok(page);
    }

    // 게시글 제목으로 조회
    @GetMapping("/group-id/{groupId}/title/{title}")
    public ResponseDTO<?> findStudyGroupBoardsByTitle(@PathVariable("groupId") Long groupId,
                                                      @PathVariable("title") String title,
                                                      @RequestParam("page") Integer pageNo) {
        PageDTO<StudyGroupBoardDTO> page =
                studyGroupBoardService.findStudyGroupBoardsByTitle(groupId, title, pageNo);
        return ResponseDTO.ok(page);
    }

    // 게시글 내용으로 조회
    @GetMapping("/group-id/{groupId}/content/{content}")
    public ResponseDTO<?> findStudyGroupBoardsByContent(@PathVariable Long groupId,
                                                        @PathVariable("content") String content,
                                                        @RequestParam("page") Integer pageNo) {
        PageDTO<StudyGroupBoardDTO> page =
                studyGroupBoardService.findStudyGroupBoardsByContent(groupId, content, pageNo);
        return ResponseDTO.ok(page);
    }

    // 게시글 단건 조회
    @GetMapping("/{boardId}")
    public ResponseDTO<?> findStudyGroupBoardByBoardId(@PathVariable("boardId") Long boardId) {
        StudyGroupBoardDTO board = studyGroupBoardService.findStudyGroupBoardByBoardId(boardId);
        return ResponseDTO.ok(board);
    }

}
