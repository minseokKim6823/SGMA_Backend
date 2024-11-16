package com.springcooler.sgma.recruitmentboardreply.command.application.service;


import com.springcooler.sgma.recruitmentboard.command.domain.aggregate.RecruitmentBoard;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.RecruitmentBoardComment;
import com.springcooler.sgma.recruitmentboardlike.common.exception.CommonException;
import com.springcooler.sgma.recruitmentboardlike.common.exception.ErrorCode;
import com.springcooler.sgma.recruitmentboardreply.command.application.dto.RecruitmentBoardReplyDTO;
import com.springcooler.sgma.recruitmentboardreply.command.domain.aggregate.ActiveStatus;
import com.springcooler.sgma.recruitmentboardreply.command.domain.aggregate.AnonymousStatus;
import com.springcooler.sgma.recruitmentboardreply.command.domain.aggregate.RecruitmentBoardReply;
import com.springcooler.sgma.recruitmentboardreply.command.domain.repository.RecruitmentBoardReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;


@Service
public class RecruitmentBoardReplyServiceImpl implements RecruitmentBoardReplyService {
    private RecruitmentBoardReplyRepository recruitmentBoardReplyRepository;



    @Autowired
    public RecruitmentBoardReplyServiceImpl(RecruitmentBoardReplyRepository recruitmentBoardReplyRepository) {
        this.recruitmentBoardReplyRepository = recruitmentBoardReplyRepository;
    }



    @Override
    @Transactional
    public RecruitmentBoardReply createRecruitBoardReply(Long recruitmentBoardCommentId,RecruitmentBoardReplyDTO recruitmentBoardReplyDTO) {
        RecruitmentBoardReply recruitmentBoardReply = RecruitmentBoardReply.builder()
                .activeStatus(ActiveStatus.ACTIVE)
                .anonymousStatus(AnonymousStatus.N)
                .content(recruitmentBoardReplyDTO.getContent())
                .createdAt(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant()))
                .updatedAt(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant()))
                .userId(recruitmentBoardReplyDTO.getUserId())
                .recruitmentBoardReplyId(recruitmentBoardReplyDTO.getRecruitmentBoardReplyId())
                .recruitmentBoardCommentId(recruitmentBoardCommentId)
                .build();
        return recruitmentBoardReplyRepository.save(recruitmentBoardReply);
    }

    @Override
    @Transactional
    public RecruitmentBoardReply updateRecruitmentReply(Long recruitmentBoardReplyId, RecruitmentBoardReplyDTO recruitmentBoardReplyDTO) {
        Optional<RecruitmentBoardReply> optionalRecruitmentBoardReply = recruitmentBoardReplyRepository.findById(recruitmentBoardReplyId);
        if (optionalRecruitmentBoardReply.isPresent()) {
            RecruitmentBoardReply recruitmentBoardReply = optionalRecruitmentBoardReply.get();

            RecruitmentBoardReply updatedRecruitmentBoardReply = recruitmentBoardReply.toBuilder()
                    .content(recruitmentBoardReplyDTO.getContent())
                    .updatedAt(Timestamp.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant()))
                    .build();
            return recruitmentBoardReplyRepository.save(updatedRecruitmentBoardReply);
        } else {
            throw new CommonException(ErrorCode.NOT_FOUND_REPLY);
        }
    }

    @Override
    @Transactional
    public void deleteRecruitmentReply(Long recruitmentBoardReplyId) {
        Optional<RecruitmentBoardReply> optionalRecruitmentBoardReply = recruitmentBoardReplyRepository.findById(recruitmentBoardReplyId);
        if (optionalRecruitmentBoardReply.isPresent()) {
            recruitmentBoardReplyRepository.deleteById(recruitmentBoardReplyId);
        } else {
            throw new CommonException(ErrorCode.NOT_FOUND_REPLY);
        }
    }
}
