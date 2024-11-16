package com.springcooler.sgma.studygroupboardreply.command.application.service;

import com.springcooler.sgma.studygroupboardreply.command.application.dto.StudyGroupBoardReplyDTO;
import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.StudyGroupBoardReply;
import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.StudyGroupBoardReplyStatus;
import com.springcooler.sgma.studygroupboardreply.command.domain.repository.StudyGroupBoardReplyRepository;
import com.springcooler.sgma.studygroupboardreply.command.domain.service.DomainStudyGroupBoardReplyService;
import com.springcooler.sgma.studygroupboardreply.common.exception.CommonException;
import com.springcooler.sgma.studygroupboardreply.common.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AppStudyGroupBoardReplyServiceImpl implements AppStudyGroupBoardReplyService {

    private final ModelMapper modelMapper;
    private final DomainStudyGroupBoardReplyService domainStudyGroupBoardReplyService;
    private final StudyGroupBoardReplyRepository studyGroupBoardReplyRepository;

    @Autowired
    public AppStudyGroupBoardReplyServiceImpl(ModelMapper modelMapper,
                                              DomainStudyGroupBoardReplyService domainStudyGroupBoardReplyService,
                                              StudyGroupBoardReplyRepository studyGroupBoardReplyRepository) {
        this.modelMapper = modelMapper;
        this.domainStudyGroupBoardReplyService = domainStudyGroupBoardReplyService;
        this.studyGroupBoardReplyRepository = studyGroupBoardReplyRepository;
    }

    // 대댓글 작성
    @Transactional
    @Override
    public StudyGroupBoardReplyDTO registStudyGroupBoardReply(StudyGroupBoardReplyDTO newReply) {
        // 유효성 검사
        if(!domainStudyGroupBoardReplyService.isValidDTO(RestStatus.POST, newReply))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 기본 정보 주입
        StudyGroupBoardReplyDTO tempReply = StudyGroupBoardReplyDTO.builder()
                .content(newReply.getContent())
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .activeStatus(StudyGroupBoardReplyStatus.ACTIVE)
                .memberId(newReply.getMemberId())
                .studyGroupBoardCommentId(newReply.getStudyGroupBoardCommentId())
                .build();

        StudyGroupBoardReply reply = modelMapper.map(tempReply, StudyGroupBoardReply.class);
        studyGroupBoardReplyRepository.save(reply);

        return modelMapper.map(reply, StudyGroupBoardReplyDTO.class);
    }

    // 대댓글 수정
    @Transactional
    @Override
    public StudyGroupBoardReplyDTO modifyStudyGroupBoardReply(StudyGroupBoardReplyDTO modifyReply) {
        // 유효성 검사
        if(!domainStudyGroupBoardReplyService.isValidDTO(RestStatus.PUT, modifyReply))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 기존 엔티티 조회
        StudyGroupBoardReply existingReply = studyGroupBoardReplyRepository.findById(modifyReply.getStudyGroupBoardReplyId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD_REPLY));

        existingReply.setContent(modifyReply.getContent());
        existingReply.setUpdatedAt(LocalDateTime.now().withNano(0));
        studyGroupBoardReplyRepository.save(existingReply);

        return modelMapper.map(existingReply, StudyGroupBoardReplyDTO.class);
    }

    // 대댓글 삭제
    @Transactional
    @Override
    public void deleteStudyGroupBoardReply(Long replyId) {
        // 기존 엔티티 조회
        StudyGroupBoardReply deleteReply = studyGroupBoardReplyRepository.findById(replyId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD_REPLY));

        // 유효성 검사
        if(!domainStudyGroupBoardReplyService.isActive(deleteReply.getActiveStatus()))
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD_REPLY);

        // INACTIVE 처리
        deleteReply.setActiveStatus(StudyGroupBoardReplyStatus.INACTIVE);
        studyGroupBoardReplyRepository.save(deleteReply);
    }

}
