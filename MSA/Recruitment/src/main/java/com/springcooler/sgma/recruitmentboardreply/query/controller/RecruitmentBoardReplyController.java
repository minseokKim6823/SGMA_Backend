package com.springcooler.sgma.recruitmentboardreply.query.controller;

import com.springcooler.sgma.recruitmentboardcomment.common.ResponseDTO;
import com.springcooler.sgma.recruitmentboardreply.query.dto.RecruitmentBoardReplyDTO;
import com.springcooler.sgma.recruitmentboardreply.query.service.RecruitmentBoardReplyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryRecruitmentBoardReplyController")
@RequestMapping("api/recruitment-board-reply/")
public class RecruitmentBoardReplyController {
    RecruitmentBoardReplyService recruitmentBoardReplyService;

    @Autowired
    public RecruitmentBoardReplyController(RecruitmentBoardReplyService recruitmentBoardReplyService) {
        this.recruitmentBoardReplyService = recruitmentBoardReplyService;
    }

    @GetMapping
    @Operation(summary = "모든 대댓글 조회")
    public ResponseDTO<?> findAllRecruitmentBoardApplicantList(){
        List<RecruitmentBoardReplyDTO> recruitmentBoardReplyDTO = recruitmentBoardReplyService.findAllRecruitmentBoardReply();
        return ResponseDTO.ok(recruitmentBoardReplyDTO);
    }
    @GetMapping("{recruitmentBoardCommentId}")
    @Operation(summary = "모집글 댓글에 해당하는 모든 대댓글 조회")
    public ResponseDTO<?> findBoardCommentByRecruitmentBoardId(@PathVariable("recruitmentBoardCommentId") Long recruitmentBoardCommentId){
        List<RecruitmentBoardReplyDTO> recruitmentBoardReplyDTO = recruitmentBoardReplyService.findAllRecruitmentBoardReplyByRecruitmentBoardId(recruitmentBoardCommentId);
        return ResponseDTO.ok(recruitmentBoardReplyDTO);
    }

}
