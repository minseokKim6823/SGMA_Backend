package com.springcooler.sgma.recruitmentboardlike.command.application.service;

import com.springcooler.sgma.recruitmentboard.command.domain.aggregate.RecruitmentBoard;
import com.springcooler.sgma.recruitmentboard.command.domain.repository.RecruitmentBoardRepository;
import com.springcooler.sgma.recruitmentboardlike.command.application.dto.RecruitmentBoardLikeDTO;
import com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.RecruitmentBoardLike;
import com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.RecruitmentBoardLikeId;
import com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.recruitmentboardlike.command.domain.repository.RecruitmentBoardLikeRepository;
import com.springcooler.sgma.recruitmentboardlike.command.domain.service.DomainRecruitmentBoardLikeService;
import com.springcooler.sgma.recruitmentboardlike.common.exception.CommonException;
import com.springcooler.sgma.recruitmentboardlike.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class AppRecruitmentBoardLikeServiceImpl implements AppRecruitmentBoardLikeService {

    private final ModelMapper modelMapper;
    private final DomainRecruitmentBoardLikeService domainRecruitmentBoardLikeService;
    private final RecruitmentBoardLikeRepository recruitmentBoardLikeRepository;
    private final RecruitmentBoardRepository recruitmentBoardRepository;

    @Autowired
    public AppRecruitmentBoardLikeServiceImpl(ModelMapper modelMapper,
                                              DomainRecruitmentBoardLikeService domainRecruitmentBoardLikeService,
                                              RecruitmentBoardLikeRepository recruitmentBoardLikeRepository,
                                              RecruitmentBoardRepository recruitmentBoardRepository) {
        this.modelMapper = modelMapper;
        this.domainRecruitmentBoardLikeService = domainRecruitmentBoardLikeService;
        this.recruitmentBoardLikeRepository = recruitmentBoardLikeRepository;
        this.recruitmentBoardRepository = recruitmentBoardRepository;
    }

    // 좋아요
    @Transactional
    @Override
    public RecruitmentBoardLikeDTO registRecruitmentBoardLike(RecruitmentBoardLikeDTO newLike) {
        // 기존 RecruitmentBoard 조회
        RecruitmentBoard existingRecruitment = recruitmentBoardRepository.findById(newLike.getRecruitmentBoardId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RECRUITMENT_BOARD));

        // 유효성 검사
        if(!domainRecruitmentBoardLikeService.isValidDTO(RestStatus.POST,newLike))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        recruitmentBoardLikeRepository.save(modelMapper.map(newLike, RecruitmentBoardLike.class));

        existingRecruitment.setLikes(existingRecruitment.getLikes() + 1);
        recruitmentBoardRepository.save(existingRecruitment);

        return newLike;
    }

    // 좋아요 취소
    @Transactional
    @Override
    public void deleteRecruitmentBoardLike(Long recruitmentId, Long userId) {
        // 기존 RecruitmentBoard 조회
        RecruitmentBoard existingRecruitment = recruitmentBoardRepository.findById(recruitmentId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RECRUITMENT_BOARD));

        // 기존 엔티티 조회
        RecruitmentBoardLike existingLike =
                recruitmentBoardLikeRepository.findById(new RecruitmentBoardLikeId(userId,recruitmentId))
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LIKE));

        recruitmentBoardLikeRepository.delete(existingLike);

        existingRecruitment.setLikes(existingRecruitment.getLikes() - 1);
        recruitmentBoardRepository.save(existingRecruitment);
    }
}

