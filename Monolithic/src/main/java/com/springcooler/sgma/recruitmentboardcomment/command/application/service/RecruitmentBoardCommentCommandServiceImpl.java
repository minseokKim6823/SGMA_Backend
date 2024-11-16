package com.springcooler.sgma.recruitmentboardcomment.command.application.service;

import com.springcooler.sgma.recruitmentboardcomment.command.application.dto.RecruitmentBoardCommentCommandDTO;
import com.springcooler.sgma.recruitmentboardcomment.command.application.service.RecruitmentBoardCommentCommandService;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.ActiveStatus;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.AnonymousStatus;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.RecruitmentBoardComment;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.repository.RecruitmentBoardCommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class RecruitmentBoardCommentCommandServiceImpl implements RecruitmentBoardCommentCommandService {

    private final RecruitmentBoardCommentRepository recruitmentBoardCommentRepository;

    @Autowired
    public RecruitmentBoardCommentCommandServiceImpl(RecruitmentBoardCommentRepository recruitmentBoardCommentRepository) {
        this.recruitmentBoardCommentRepository = recruitmentBoardCommentRepository;
    }

    @Transactional
    @Override
    public RecruitmentBoardComment createRecruitmentBoardComment(Long recruitmentBoardId,RecruitmentBoardCommentCommandDTO recruitmentBoardCommentCommandDTO) {
        RecruitmentBoardComment recruitmentBoardComment = RecruitmentBoardComment.builder()
                .content(recruitmentBoardCommentCommandDTO.getContent())
                .createdAt(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant()))
                .updatedAt(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant()))
                .userId(recruitmentBoardCommentCommandDTO.getUserId())
                .activeStatus(ActiveStatus.ACTIVE)
                .recruitmentBoardId(recruitmentBoardId)
                .anonymousStatus(AnonymousStatus.valueOf(recruitmentBoardCommentCommandDTO.getAnonymousStatus()))
                .build();

        return recruitmentBoardCommentRepository.save(recruitmentBoardComment);
    }

    @Override
    @Transactional
    public RecruitmentBoardComment updateRecruitmentBoardComment(Long recruitmentBoardCommentId, RecruitmentBoardCommentCommandDTO recruitmentBoardCommentCommandDTO) {
        Optional<RecruitmentBoardComment> optionalRecruitmentBoardComment = recruitmentBoardCommentRepository.findById(recruitmentBoardCommentId);

        if (optionalRecruitmentBoardComment.isPresent()) {
            RecruitmentBoardComment recruitmentBoardComment = optionalRecruitmentBoardComment.get();
            recruitmentBoardComment.setContent(recruitmentBoardCommentCommandDTO.getContent());
            recruitmentBoardComment.setUpdatedAt(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant()));

            return recruitmentBoardCommentRepository.save(recruitmentBoardComment);
        } else {
            throw new EntityNotFoundException("수정할 댓글이 없습니다.");
        }
    }


    @Override
    @Transactional
    public RecruitmentBoardComment deleteRecruitmentBoardComment(Long recruitmentBoardCommentId, RecruitmentBoardCommentCommandDTO recruitmentBoardCommentCommandDTO) {
        Optional<RecruitmentBoardComment> optionalRecruitmentBoardComment = recruitmentBoardCommentRepository.findById(recruitmentBoardCommentId);

        if (optionalRecruitmentBoardComment.isPresent()) {
            RecruitmentBoardComment recruitmentBoardComment = optionalRecruitmentBoardComment.get();

            RecruitmentBoardComment updatedRecruitmentBoardComment = recruitmentBoardComment.toBuilder()
                    .activeStatus(ActiveStatus.INACTIVE)
                    .build();

            return recruitmentBoardCommentRepository.save(updatedRecruitmentBoardComment);
        } else {
            throw new EntityNotFoundException("삭제할 댓글이 없습니다.");
        }
    }
}