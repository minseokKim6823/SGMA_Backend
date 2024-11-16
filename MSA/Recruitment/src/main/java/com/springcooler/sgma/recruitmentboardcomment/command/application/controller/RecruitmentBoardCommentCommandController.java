package com.springcooler.sgma.recruitmentboardcomment.command.application.controller;


import com.springcooler.sgma.recruitmentboardcomment.command.application.dto.RecruitmentBoardCommentCommandDTO;
import com.springcooler.sgma.recruitmentboardcomment.command.application.service.RecruitmentBoardCommentCommandService;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.RecruitmentBoardComment;
import com.springcooler.sgma.recruitmentboardcomment.common.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruitment-board-comments")
public class RecruitmentBoardCommentCommandController {

    private final RecruitmentBoardCommentCommandService recruitmentBoardCommentCommandService;

    @Autowired
    public RecruitmentBoardCommentCommandController(RecruitmentBoardCommentCommandService recruitmentBoardCommentCommandService) {
        this.recruitmentBoardCommentCommandService = recruitmentBoardCommentCommandService;
    }

    @PostMapping("/{recruitmentBoardId}")
    @Operation(summary = "모집글 댓글 작성")
    public ResponseDTO<?> createComment(@PathVariable("recruitmentBoardId") Long recruitmentBoardId, @RequestBody RecruitmentBoardCommentCommandDTO recruitmentBoardCommentCommandDTO) {
        RecruitmentBoardComment createdComment = recruitmentBoardCommentCommandService.createRecruitmentBoardComment(recruitmentBoardId, recruitmentBoardCommentCommandDTO);
        return ResponseDTO.ok(createdComment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "모집글 댓글 수정")
    public ResponseDTO<?> updateComment(@PathVariable("id") Long id, @RequestBody RecruitmentBoardCommentCommandDTO recruitmentBoardCommentCommandDTO) {
        RecruitmentBoardComment updatedComment = recruitmentBoardCommentCommandService.updateRecruitmentBoardComment(id, recruitmentBoardCommentCommandDTO);
        return ResponseDTO.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "모집글 댓글 삭제")
    public ResponseDTO<?> deleteComment(@PathVariable("id") Long id) {
        recruitmentBoardCommentCommandService.deleteRecruitmentBoardComment(id);
        return ResponseDTO.ok(HttpStatus.NO_CONTENT);
    }
}