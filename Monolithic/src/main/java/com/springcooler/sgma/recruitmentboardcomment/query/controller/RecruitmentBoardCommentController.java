package com.springcooler.sgma.recruitmentboardcomment.query.controller;

import com.springcooler.sgma.recruitmentboardcomment.common.ResponseDTO;
import com.springcooler.sgma.recruitmentboardcomment.query.dto.RecruitmentBoardCommentDTO;
import com.springcooler.sgma.recruitmentboardcomment.query.service.RecruitmentBoardCommentService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecruitmentBoardCommentController {
    private RecruitmentBoardCommentService recruitmentBoardCommentService;

    @Autowired
    public RecruitmentBoardCommentController(RecruitmentBoardCommentService recruitmentBoardCommentService) {
        this.recruitmentBoardCommentService = recruitmentBoardCommentService;
    }

    @GetMapping("getAll/BoardCommand/{recruitmentBoardId}")
    @Operation(summary = "모집글에 해당하는 모든 댓글 조회")
    public ResponseDTO<?> findBoardCommentByRecruitmentBoardId(@PathVariable Long recruitmentBoardId){
        List<RecruitmentBoardCommentDTO> recruitmentBoardCommentDTO = recruitmentBoardCommentService.studyGroupRecruitmentComment(recruitmentBoardId);
        return ResponseDTO.ok(recruitmentBoardCommentDTO);
    }
}
