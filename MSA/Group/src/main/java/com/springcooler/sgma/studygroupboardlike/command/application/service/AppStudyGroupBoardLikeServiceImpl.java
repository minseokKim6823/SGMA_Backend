package com.springcooler.sgma.studygroupboardlike.command.application.service;

import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;
import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.StudyGroupBoardLike;
import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.StudyGroupBoardLikePK;
import com.springcooler.sgma.studygroupboardlike.command.domain.repository.StudyGroupBoardLikeRepository;
import com.springcooler.sgma.studygroupboardlike.command.domain.service.DomainStudyGroupBoardLikeService;
import com.springcooler.sgma.studygroupboardlike.common.exception.CommonException;
import com.springcooler.sgma.studygroupboardlike.common.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppStudyGroupBoardLikeServiceImpl implements AppStudyGroupBoardLikeService {

    private final ModelMapper modelMapper;
    private final DomainStudyGroupBoardLikeService domainStudyGroupBoardLikeService;
    private final StudyGroupBoardLikeRepository studyGroupBoardLikeRepository;

    @Autowired
    public AppStudyGroupBoardLikeServiceImpl(ModelMapper modelMapper,
                                             DomainStudyGroupBoardLikeService domainStudyGroupBoardLikeService,
                                             StudyGroupBoardLikeRepository studyGroupBoardLikeRepository) {
        this.modelMapper = modelMapper;
        this.domainStudyGroupBoardLikeService = domainStudyGroupBoardLikeService;
        this.studyGroupBoardLikeRepository = studyGroupBoardLikeRepository;
    }

    // 좋아요
    @Transactional
    @Override
    public StudyGroupBoardLikeDTO registStudyGroupBoardLike(StudyGroupBoardLikeDTO newLike) {
        // 유효성 검사
        if(!domainStudyGroupBoardLikeService.isValidDTO(RestStatus.POST,newLike))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        studyGroupBoardLikeRepository.save(modelMapper.map(newLike, StudyGroupBoardLike.class));

        return newLike;
    }

    // 좋아요 취소
    @Transactional
    @Override
    public void deleteStudyGroupBoardLike(Long boardId, Long memberId) {
        // 기존 엔티티 조회
        StudyGroupBoardLike existingLike =
                studyGroupBoardLikeRepository.findById(new StudyGroupBoardLikePK(boardId,memberId))
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LIKE));

        studyGroupBoardLikeRepository.delete(existingLike);
    }

}
