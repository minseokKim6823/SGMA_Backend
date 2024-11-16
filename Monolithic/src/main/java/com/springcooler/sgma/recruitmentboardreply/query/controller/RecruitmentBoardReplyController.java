package com.springcooler.sgma.recruitmentboardreply.query.controller;

import com.springcooler.sgma.recruitmentboardcomment.common.ResponseDTO;
import com.springcooler.sgma.recruitmentboardreply.query.dto.RecruitmentBoardReplyDTO;
import com.springcooler.sgma.recruitmentboardreply.query.service.RecruitmentBoardReplyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("RecruitmentBoardReplyCommandController")
@RequestMapping("api/reply/")
public class RecruitmentBoardReplyController {
    RecruitmentBoardReplyService recruitmentBoardReplyService;

    @Autowired
    public RecruitmentBoardReplyController(RecruitmentBoardReplyService recruitmentBoardReplyService) {
        this.recruitmentBoardReplyService = recruitmentBoardReplyService;
    }

    @GetMapping("getAllReply")
    @Operation(summary = "모든 대댓글 조회")
    public ResponseDTO<?> findAllRecruitmentBoardApplicantList(){
        List<RecruitmentBoardReplyDTO> recruitmentBoardReplyDTO = recruitmentBoardReplyService.getAllRecruitmentBoardReply();
        return ResponseDTO.ok(recruitmentBoardReplyDTO);
    }

}
