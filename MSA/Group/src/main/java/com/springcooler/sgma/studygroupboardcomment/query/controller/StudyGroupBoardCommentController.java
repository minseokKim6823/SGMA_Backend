package com.springcooler.sgma.studygroupboardcomment.query.controller;

import com.springcooler.sgma.studygroupboardcomment.common.ResponseDTO;
import com.springcooler.sgma.studygroupboardcomment.query.dto.StudyGroupBoardCommentDTO;
import com.springcooler.sgma.studygroupboardcomment.query.service.StudyGroupBoardCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryStudyGroupBoardCommentController")
@RequestMapping("/api/study-group/board/comments")
public class StudyGroupBoardCommentController {

    private final StudyGroupBoardCommentService studyGroupBoardCommentService;

    @Autowired
    public StudyGroupBoardCommentController(StudyGroupBoardCommentService studyGroupBoardCommentService) {
        this.studyGroupBoardCommentService = studyGroupBoardCommentService;
    }

    // 게시글별 댓글 전체 조회
    @GetMapping("/board-id/{boardId}")
    public ResponseDTO<?> findStudyGroupBoardCommentsByBoardId(@PathVariable("boardId") Long boardId) {
        List<StudyGroupBoardCommentDTO> comments =
                studyGroupBoardCommentService.findStudyGroupBoardCommentsByBoardId(boardId);
        return ResponseDTO.ok(comments);
    }

    // 그룹원별 댓글 전체 조회
    @GetMapping("/member-id/{memberId}")
    public ResponseDTO<?> findStudyGroupBoardCommentsByMemberId(@PathVariable("memberId") Long memberId) {
        List<StudyGroupBoardCommentDTO> comments =
                studyGroupBoardCommentService.findStudyGroupBoardCommentsByMemberId(memberId);
        return ResponseDTO.ok(comments);
    }

}
