package com.springcooler.sgma.studygroupboardlike.query.controller;

import com.springcooler.sgma.studygroupboardlike.common.ResponseDTO;
import com.springcooler.sgma.studygroupboardlike.query.dto.StudyGroupBoardLikeDTO;
import com.springcooler.sgma.studygroupboardlike.query.service.StudyGroupBoardLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryStudyGroupBoardLikeController")
@RequestMapping("/api/study-group/board/likes")
public class StudyGroupBoardLikeController {

    private final StudyGroupBoardLikeService studyGroupBoardLikeService;

    @Autowired
    public StudyGroupBoardLikeController(StudyGroupBoardLikeService studyGroupBoardLikeService) {
        this.studyGroupBoardLikeService = studyGroupBoardLikeService;
    }

    // 게시글 아이디로 좋아요 조회
    @GetMapping("/board-id/{boardId}")
    public ResponseDTO<?> findStudyGroupBoardLikesByBoardId(@PathVariable("boardId") Long boardId) {
        List<StudyGroupBoardLikeDTO> likes = studyGroupBoardLikeService.findStudyGroupBoardLikesByBoardId(boardId);
        return ResponseDTO.ok(likes);
    }

    // 그룹원 아이디로 좋아요 조회
    @GetMapping("/member-id/{memberId}")
    public ResponseDTO<?> findStudyGroupBoardLikesByMemberId(@PathVariable("memberId") Long memberId) {
        List<StudyGroupBoardLikeDTO> likes = studyGroupBoardLikeService.findStudyGroupBoardLikesByMemberId(memberId);
        return ResponseDTO.ok(likes);
    }

}
