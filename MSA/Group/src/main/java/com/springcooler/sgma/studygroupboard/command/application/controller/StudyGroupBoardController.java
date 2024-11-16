package com.springcooler.sgma.studygroupboard.command.application.controller;

import com.springcooler.sgma.studygroupboard.command.application.dto.StudyGroupBoardDTO;
import com.springcooler.sgma.studygroupboard.command.application.service.AppStudyGroupBoardService;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.vo.RequestStudyGroupBoardVO;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.vo.ResponseStudyGroupBoardVO;
import com.springcooler.sgma.studygroupboard.common.ResponseDTO;
import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;
import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.vo.RequestStudyGroupBoardLikeVO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandStudyGroupBoardController")
@RequestMapping("/api/study-group/boards")
@Slf4j
public class StudyGroupBoardController {

    private final ModelMapper modelMapper;
    private final AppStudyGroupBoardService studyGroupBoardService;

    @Autowired
    public StudyGroupBoardController(ModelMapper modelMapper,
                                     AppStudyGroupBoardService studyGroupBoardService) {
        this.modelMapper = modelMapper;
        this.studyGroupBoardService = studyGroupBoardService;
    }

    // 게시글 작성
    @PostMapping
    public ResponseDTO<?> registStudyGroupBoard(@RequestBody RequestStudyGroupBoardVO newBoard) {
        StudyGroupBoardDTO board = modelMapper.map(newBoard, StudyGroupBoardDTO.class);
        board = studyGroupBoardService.registStudyGroupBoard(board);
        ResponseStudyGroupBoardVO res = modelMapper.map(board, ResponseStudyGroupBoardVO.class);
        return ResponseDTO.ok(res);
    }

    // 좋아요
    @PostMapping("/like")
    public ResponseDTO<?> registStudyGroupBoardLike(@RequestBody RequestStudyGroupBoardLikeVO newLike) {
        StudyGroupBoardLikeDTO like = modelMapper.map(newLike, StudyGroupBoardLikeDTO.class);
        StudyGroupBoardDTO board = studyGroupBoardService.registStudyGroupBoardLike(like);
        ResponseStudyGroupBoardVO res = modelMapper.map(board, ResponseStudyGroupBoardVO.class);
        return ResponseDTO.ok(res);
    }

    // 게시글 수정
    @PutMapping
    public ResponseDTO<?> modifyStudyGroupBoard(@RequestBody RequestStudyGroupBoardVO modifyBoard) {
        StudyGroupBoardDTO board = modelMapper.map(modifyBoard, StudyGroupBoardDTO.class);
        board = studyGroupBoardService.modifyStudyGroupBoard(board);
        ResponseStudyGroupBoardVO res = modelMapper.map(board, ResponseStudyGroupBoardVO.class);
        return ResponseDTO.ok(res);
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseDTO<?> deleteStudyGroupBoard(@PathVariable("boardId") Long boardId) {
        studyGroupBoardService.deleteStudyGroupBoard(boardId);
        return ResponseDTO.ok(null);
    }

    // 좋아요 취소
    @DeleteMapping("/like")
    public ResponseDTO<?> deleteStudyGroupBoardLike(@RequestParam("board-id") Long boardId,
                                                    @RequestParam("member-id") Long memberId) {
        StudyGroupBoardDTO board = studyGroupBoardService.deleteStudyGroupBoardLike(boardId, memberId);
        ResponseStudyGroupBoardVO res = modelMapper.map(board, ResponseStudyGroupBoardVO.class);
        return ResponseDTO.ok(res);
    }

}
