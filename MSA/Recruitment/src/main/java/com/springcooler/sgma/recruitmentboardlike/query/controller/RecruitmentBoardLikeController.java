package com.springcooler.sgma.recruitmentboardlike.query.controller;


import com.springcooler.sgma.recruitmentboardcomment.common.ResponseDTO;
import com.springcooler.sgma.recruitmentboardlike.query.dto.RecruitmentBoardLikeDTO;
import com.springcooler.sgma.recruitmentboardlike.query.service.RecruitmentBoardLikeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryRecruitmentBoardLikeController")
@RequestMapping("api/recruitment-board-like")
public class RecruitmentBoardLikeController {
    RecruitmentBoardLikeService recruitmentBoardLikeService;

    @Autowired
    public RecruitmentBoardLikeController(RecruitmentBoardLikeService recruitmentBoardLikeService) {
        this.recruitmentBoardLikeService = recruitmentBoardLikeService;
    }
    @GetMapping
    @Operation(summary = "모집글 좋아요 전체 조회")
    public ResponseDTO<?> findAllRecruimentBoardLikes(){
        List<RecruitmentBoardLikeDTO> recruitmentBoardLikes =recruitmentBoardLikeService.findAllRecruitmentBoardLike();
        return ResponseDTO.ok(recruitmentBoardLikes);
    }

    @GetMapping("user/{userId}")
    @Operation(summary = "모집글 좋아요 사용자ID 기준 조회")
    public ResponseDTO<?> findRecruimentBoardLikesByUserId(@PathVariable("userId") Long userId){
        List<RecruitmentBoardLikeDTO> recruitmentBoardLikes =recruitmentBoardLikeService.findrecruitmentBoardLikeByUserId(userId);
        return ResponseDTO.ok(recruitmentBoardLikes);
    }
    @GetMapping("recruitmentboard/{recruitmentBoardId}")
    @Operation(summary = "모집글 좋아요 모집글ID 기준 조회")
    public ResponseDTO<?> findRecruimentBoardLikesByRecruitmentBoardId(@PathVariable("recruitmentBoardId") Long recruitmentBoardId){
        List<RecruitmentBoardLikeDTO> recruitmentBoardLikes =recruitmentBoardLikeService.findrecruitmentBoardLikeByRecruitmentBoardId(recruitmentBoardId);
        return ResponseDTO.ok(recruitmentBoardLikes);
    }
}
