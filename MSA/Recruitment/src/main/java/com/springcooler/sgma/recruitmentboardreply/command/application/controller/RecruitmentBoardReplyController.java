package com.springcooler.sgma.recruitmentboardreply.command.application.controller;

import com.springcooler.sgma.recruitmentboard.command.application.dto.RecruitmentBoardCommandDTO;
import com.springcooler.sgma.recruitmentboard.common.ResponseDTO;
import com.springcooler.sgma.recruitmentboardreply.command.application.dto.RecruitmentBoardReplyDTO;
import com.springcooler.sgma.recruitmentboardreply.command.application.service.RecruitmentBoardReplyService;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/recruitment-board-reply")
@RequiredArgsConstructor
public class RecruitmentBoardReplyController {
    private final RecruitmentBoardReplyService recruitmentBoardReplyService;


    @PostMapping("/{recruitmentBoardCommentId}")
    @Operation(summary = "댓글에 대한 대댓글 작성")
    public ResponseDTO<?> writeReply(@PathVariable("recruitmentBoardCommentId") Long recruitmentBoardCommentId,@RequestBody RecruitmentBoardReplyDTO recruitmentBoardReplyDTO){
        recruitmentBoardReplyService.createRecruitBoardReply(recruitmentBoardCommentId,recruitmentBoardReplyDTO);
        return ResponseDTO.ok(recruitmentBoardReplyDTO);
    }

    @PutMapping("/{recruitmentBoardId}")
    @Operation(summary = "댓글에 대한 대댓글 수정")
    public ResponseDTO<?> updateReply(@PathVariable("recruitmentBoardId") Long recruitmentBoardId, @RequestBody RecruitmentBoardReplyDTO recruitmentBoardReplyDTO) {
        recruitmentBoardReplyService.updateRecruitmentReply(recruitmentBoardId, recruitmentBoardReplyDTO);
        return ResponseDTO.ok(recruitmentBoardReplyDTO);
    }

    @DeleteMapping("/{recruitmentBoardId}")
    @Operation(summary = "댓글에 대한 대댓글 삭제")
    public ResponseDTO<?> deleteReply(@PathVariable("recruitmentBoardId") Long recruitmentBoardId) {
        recruitmentBoardReplyService.deleteRecruitmentReply(recruitmentBoardId);
        return ResponseDTO.ok("ok");
    }
}
