package com.springcooler.sgma.studygroupboardreply.command.application.controller;

import com.springcooler.sgma.studygroupboardreply.command.application.dto.StudyGroupBoardReplyDTO;
import com.springcooler.sgma.studygroupboardreply.command.application.service.AppStudyGroupBoardReplyService;
import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.vo.RequestStudyGroupBoardReplyVO;
import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.vo.ResponseStudyGroupBoardReplyVO;
import com.springcooler.sgma.studygroupboardreply.common.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandStudyGroupBoardReplyController")
@RequestMapping("/api/study-group/board/replies")
public class StudyGroupBoardReplyController {

    private final ModelMapper modelMapper;
    private final AppStudyGroupBoardReplyService studyGroupBoardReplyService;

    @Autowired
    public StudyGroupBoardReplyController(ModelMapper modelMapper,
                                          AppStudyGroupBoardReplyService studyGroupBoardReplyService) {
        this.modelMapper = modelMapper;
        this.studyGroupBoardReplyService = studyGroupBoardReplyService;
    }

    @PostMapping
    public ResponseDTO<?> registStudyGroupBoardReply(@RequestBody RequestStudyGroupBoardReplyVO newReply) {
        StudyGroupBoardReplyDTO reply = modelMapper.map(newReply, StudyGroupBoardReplyDTO.class);
        reply = studyGroupBoardReplyService.registStudyGroupBoardReply(reply);
        ResponseStudyGroupBoardReplyVO res = modelMapper.map(reply, ResponseStudyGroupBoardReplyVO.class);
        return ResponseDTO.ok(res);
    }

    @PutMapping
    public ResponseDTO<?> modifyStudyGroupBoardReply(@RequestBody RequestStudyGroupBoardReplyVO modifyReply) {
        StudyGroupBoardReplyDTO reply = modelMapper.map(modifyReply, StudyGroupBoardReplyDTO.class);
        reply = studyGroupBoardReplyService.modifyStudyGroupBoardReply(reply);
        ResponseStudyGroupBoardReplyVO res = modelMapper.map(reply, ResponseStudyGroupBoardReplyVO.class);
        return ResponseDTO.ok(res);
    }

    @DeleteMapping("/{replyId}")
    public ResponseDTO<?> deleteStudyGroupBoardReply(@PathVariable("replyId") Long replyId) {
        studyGroupBoardReplyService.deleteStudyGroupBoardReply(replyId);
        return ResponseDTO.ok(null);
    }

}
