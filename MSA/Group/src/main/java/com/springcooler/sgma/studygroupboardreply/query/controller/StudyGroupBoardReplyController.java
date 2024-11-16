package com.springcooler.sgma.studygroupboardreply.query.controller;

import com.springcooler.sgma.studygroupboardreply.common.ResponseDTO;
import com.springcooler.sgma.studygroupboardreply.query.dto.StudyGroupBoardReplyDTO;
import com.springcooler.sgma.studygroupboardreply.query.service.StudyGroupBoardReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryStudyGroupBoardReplyController")
@RequestMapping("/api/study-group/board/replies")
public class StudyGroupBoardReplyController {

    private final StudyGroupBoardReplyService studyGroupBoardReplyService;

    @Autowired
    public StudyGroupBoardReplyController(StudyGroupBoardReplyService studyGroupBoardReplyService) {
        this.studyGroupBoardReplyService = studyGroupBoardReplyService;
    }

    @GetMapping("/comment-id/{commentId}")
    public ResponseDTO<?> findStudyGroupBoardRepliesByCommentId(@PathVariable("commentId") Long commentId) {
        List<StudyGroupBoardReplyDTO> replies =
                studyGroupBoardReplyService.findStudyGroupBoardRepliesByCommentId(commentId);
        return ResponseDTO.ok(replies);
    }

    @GetMapping("/member-id/{memberId}")
    public ResponseDTO<?> findStudyGroupBoardRepliesByMemberId(@PathVariable("memberId") Long memberId) {
        List<StudyGroupBoardReplyDTO> replies =
                studyGroupBoardReplyService.findStudyGroupBoardRepliesByMemberId(memberId);
        return ResponseDTO.ok(replies);
    }

}
