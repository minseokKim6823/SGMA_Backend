package com.springcooler.sgma.studygroupboardcomment.command.application.service;

import com.springcooler.sgma.studygroupboardcomment.command.application.dto.StudyGroupBoardCommentDTO;
import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.StudyGroupBoardComment;
import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.StudyGroupBoardCommentStatus;
import com.springcooler.sgma.studygroupboardcomment.command.domain.repository.StudyGroupBoardCommentRepository;
import com.springcooler.sgma.studygroupboardcomment.command.domain.service.DomainStudyGroupBoardCommentService;
import com.springcooler.sgma.studygroupboardcomment.common.exception.CommonException;
import com.springcooler.sgma.studygroupboardcomment.common.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AppStudyGroupBoardCommentServiceImpl implements AppStudyGroupBoardCommentService {

    private final ModelMapper modelMapper;
    private final DomainStudyGroupBoardCommentService domainStudyGroupBoardCommentService;
    private final StudyGroupBoardCommentRepository studyGroupBoardCommentRepository;

    @Autowired
    public AppStudyGroupBoardCommentServiceImpl(ModelMapper modelMapper,
                                                DomainStudyGroupBoardCommentService domainStudyGroupBoardCommentService,
                                                StudyGroupBoardCommentRepository studyGroupBoardCommentRepository) {
        this.modelMapper = modelMapper;
        this.domainStudyGroupBoardCommentService = domainStudyGroupBoardCommentService;
        this.studyGroupBoardCommentRepository = studyGroupBoardCommentRepository;
    }

    // 댓글 작성
    @Transactional
    @Override
    public StudyGroupBoardCommentDTO registStudyGroupBoardComment(StudyGroupBoardCommentDTO newComment) {
        // DTO 유효성 검사
        if(!domainStudyGroupBoardCommentService.isValidDTO(RestStatus.POST, newComment))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 댓글 기본 정보 주입
        StudyGroupBoardCommentDTO tempComment = StudyGroupBoardCommentDTO.builder()
                .content(newComment.getContent())
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .activeStatus(StudyGroupBoardCommentStatus.ACTIVE)
                .memberId(newComment.getMemberId())
                .studyGroupBoardId(newComment.getStudyGroupBoardId())
                .build();

        StudyGroupBoardComment comment = modelMapper.map(tempComment, StudyGroupBoardComment.class);
        comment = studyGroupBoardCommentRepository.save(comment);

        return modelMapper.map(comment, StudyGroupBoardCommentDTO.class);
    }

    // 댓글 수정
    @Transactional
    @Override
    public StudyGroupBoardCommentDTO modifyStudyGroupBoardComment(StudyGroupBoardCommentDTO modifyComment) {
        // DTO 유효성 검사
        if(!domainStudyGroupBoardCommentService.isValidDTO(RestStatus.PUT, modifyComment))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 기존 엔티티 조회
        StudyGroupBoardComment existingComment = studyGroupBoardCommentRepository
                .findById(modifyComment.getStudyGroupBoardCommentId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD_COMMENT));

        // 바뀐 정보로 업데이트
        existingComment.setContent(modifyComment.getContent());
        existingComment.setUpdatedAt(LocalDateTime.now().withNano(0));

        studyGroupBoardCommentRepository.save(existingComment);

        return modelMapper.map(existingComment, StudyGroupBoardCommentDTO.class);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public void deleteStudyGroupBoardComment(Long commentId) {
        // 기존 엔티티 조회
        StudyGroupBoardComment deleteComment = studyGroupBoardCommentRepository.findById(commentId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD_COMMENT));

        // 유효성 검사
        if(!domainStudyGroupBoardCommentService.isActive(deleteComment.getActiveStatus()))
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD_COMMENT);

        // INACTIVE 처리
        deleteComment.setActiveStatus(StudyGroupBoardCommentStatus.INACTIVE);
        studyGroupBoardCommentRepository.save(deleteComment);
    }

}
