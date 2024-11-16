package com.springcooler.sgma.studygroupboardlike.command.application.controller;

import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;
import com.springcooler.sgma.studygroupboardlike.command.application.service.AppStudyGroupBoardLikeService;
import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.vo.RequestStudyGroupBoardLikeVO;
import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.vo.ResponseStudyGroupBoardLikeVO;
import com.springcooler.sgma.studygroupboardlike.common.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandStudyGroupBoardLikeController")
@RequestMapping("/api/study-group/board/likes")
public class StudyGroupBoardLikeController {

    private final ModelMapper modelMapper;
    private final AppStudyGroupBoardLikeService studyGroupBoardLikeService;

    @Autowired
    public StudyGroupBoardLikeController(ModelMapper modelMapper,
                                         AppStudyGroupBoardLikeService studyGroupBoardLikeService) {
        this.modelMapper = modelMapper;
        this.studyGroupBoardLikeService = studyGroupBoardLikeService;
    }

    // 좋아요
    @PostMapping
    public ResponseDTO<?> registStudyGroupBoardLike(@RequestBody RequestStudyGroupBoardLikeVO newLike) {
        StudyGroupBoardLikeDTO like = modelMapper.map(newLike, StudyGroupBoardLikeDTO.class);
        like = studyGroupBoardLikeService.registStudyGroupBoardLike(like);
        ResponseStudyGroupBoardLikeVO res = modelMapper.map(like, ResponseStudyGroupBoardLikeVO.class);
        return ResponseDTO.ok(res);
    }

    // 좋아요 취소
    @DeleteMapping
    public ResponseDTO<?> deleteStudyGroupBoardLike(@RequestParam("board-id") Long boardId,
                                                    @RequestParam("member-id") Long memberId) {
        studyGroupBoardLikeService.deleteStudyGroupBoardLike(boardId, memberId);
        return ResponseDTO.ok(null);
    }

}
