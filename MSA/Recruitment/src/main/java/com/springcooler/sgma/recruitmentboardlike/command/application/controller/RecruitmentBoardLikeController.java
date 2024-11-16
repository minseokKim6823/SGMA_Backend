package com.springcooler.sgma.recruitmentboardlike.command.application.controller;

import com.springcooler.sgma.recruitmentboardlike.command.application.dto.RecruitmentBoardLikeDTO;
import com.springcooler.sgma.recruitmentboardlike.command.application.service.AppRecruitmentBoardLikeService;
import com.springcooler.sgma.recruitmentboardcomment.common.ResponseDTO;
import com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.vo.RequestRecruitmentBoardLikeVO;
import com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.vo.ResponseRecruitmentBoardLikeVO;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/recruitment-board/likes")
public class RecruitmentBoardLikeController {

    private final ModelMapper modelMapper;
    private final AppRecruitmentBoardLikeService recruitmentBoardLikeService;

    @Autowired
    public RecruitmentBoardLikeController(ModelMapper modelMapper,
                                         AppRecruitmentBoardLikeService recruitmentBoardLikeService) {
        this.modelMapper = modelMapper;
        this.recruitmentBoardLikeService = recruitmentBoardLikeService;
    }

    @PostMapping
    @Operation(summary = "모집글 좋아요 추가")
    public ResponseDTO<?> registRecruitmentBoardLike(@RequestBody RequestRecruitmentBoardLikeVO newLike) {
        RecruitmentBoardLikeDTO like = modelMapper.map(newLike, RecruitmentBoardLikeDTO.class);
        like = recruitmentBoardLikeService.registRecruitmentBoardLike(like);
        ResponseRecruitmentBoardLikeVO res = modelMapper.map(like, ResponseRecruitmentBoardLikeVO.class);
        return ResponseDTO.ok(res);
    }

    @DeleteMapping
    @Operation(summary = "모집글 좋아요 취소")
    public ResponseDTO<?> deleteStudyGroupBoardLike(@RequestParam("recruitment-id") Long recruitmentId,
                                                    @RequestParam("user-id") Long userId) {
        recruitmentBoardLikeService.deleteRecruitmentBoardLike(recruitmentId, userId);
        return ResponseDTO.ok(null);
    }
}
