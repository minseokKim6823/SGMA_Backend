package com.springcooler.sgma.studygroupboardcomment.command.application.controller;

import com.springcooler.sgma.studygroupboardcomment.command.application.dto.StudyGroupBoardCommentDTO;
import com.springcooler.sgma.studygroupboardcomment.command.application.service.AppStudyGroupBoardCommentService;
import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.vo.RequestStudyGroupBoardCommentVO;
import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.vo.ResponseStudyGroupBoardCommentVO;
import com.springcooler.sgma.studygroupboardcomment.common.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandStudyGroupBoardCommentController")
@RequestMapping("/api/study-group/board/comments")
public class StudyGroupBoardCommentController {

    private final ModelMapper modelMapper;
    private final AppStudyGroupBoardCommentService studyGroupBoardCommentService;

    @Autowired
    public StudyGroupBoardCommentController(ModelMapper modelMapper,
                                            AppStudyGroupBoardCommentService studyGroupBoardCommentService) {
        this.modelMapper = modelMapper;
        this.studyGroupBoardCommentService = studyGroupBoardCommentService;
    }

    @PostMapping
    public ResponseDTO<?> registStudyGroupBoardComment(@RequestBody RequestStudyGroupBoardCommentVO newComment) {
        StudyGroupBoardCommentDTO comment = modelMapper.map(newComment, StudyGroupBoardCommentDTO.class);
        comment = studyGroupBoardCommentService.registStudyGroupBoardComment(comment);
        ResponseStudyGroupBoardCommentVO res = modelMapper.map(comment, ResponseStudyGroupBoardCommentVO.class);
        return ResponseDTO.ok(res);
    }

    @PutMapping
    public ResponseDTO<?> modifyStudyGroupBoardComment(@RequestBody RequestStudyGroupBoardCommentVO modifyComment) {
        StudyGroupBoardCommentDTO comment = modelMapper.map(modifyComment, StudyGroupBoardCommentDTO.class);
        comment = studyGroupBoardCommentService.modifyStudyGroupBoardComment(comment);
        ResponseStudyGroupBoardCommentVO res = modelMapper.map(comment, ResponseStudyGroupBoardCommentVO.class);
        return ResponseDTO.ok(res);
    }

    @DeleteMapping("/{commentId}")
    public ResponseDTO<?> deleteStudyGroupBoardComment(@PathVariable("commentId") Long commentId) {
        studyGroupBoardCommentService.deleteStudyGroupBoardComment(commentId);
        return ResponseDTO.ok(null);
    }

}
