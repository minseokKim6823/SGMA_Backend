package com.springcooler.sgma.studygroupboard.command.application.service;

import com.springcooler.sgma.studygroupboard.command.application.dto.StudyGroupBoardDTO;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.StudyGroupBoard;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.StudyGroupBoardStatus;
import com.springcooler.sgma.studygroupboard.command.domain.repository.StudyGroupBoardRepository;
import com.springcooler.sgma.studygroupboard.command.domain.service.DomainStudyGroupBoardService;
import com.springcooler.sgma.studygroupboard.command.infrastructure.service.InfraStudyGroupBoardService;
import com.springcooler.sgma.studygroupboard.common.exception.CommonException;
import com.springcooler.sgma.studygroupboard.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AppStudyGroupBoardServiceImpl implements AppStudyGroupBoardService {

    private final ModelMapper modelMapper;
    private final DomainStudyGroupBoardService domainStudyGroupBoardService;
    private final InfraStudyGroupBoardService infraStudyGroupBoardService;
    private final StudyGroupBoardRepository studyGroupBoardRepository;

    @Autowired
    public AppStudyGroupBoardServiceImpl(ModelMapper modelMapper,
                                         DomainStudyGroupBoardService domainStudyGroupBoardService,
                                         InfraStudyGroupBoardService infraStudyGroupBoardService,
                                         StudyGroupBoardRepository studyGroupBoardRepository) {
        this.modelMapper = modelMapper;
        this.domainStudyGroupBoardService = domainStudyGroupBoardService;
        this.infraStudyGroupBoardService = infraStudyGroupBoardService;
        this.studyGroupBoardRepository = studyGroupBoardRepository;
    }

    @Transactional
    @Override
    public StudyGroupBoardDTO registStudyGroupBoard(StudyGroupBoardDTO newBoard) {
        System.out.println(newBoard);
        // DTO 유효성 검사
        if(!domainStudyGroupBoardService.isValidDTO(RestStatus.POST,newBoard))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 초기 정보를 담은 새 DTO 생성
        StudyGroupBoardDTO tempBoard = StudyGroupBoardDTO.builder()
                .title(newBoard.getTitle())
                .content(newBoard.getContent())
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .activeStatus(StudyGroupBoardStatus.ACTIVE)
                .likes(0)
                .memberId(newBoard.getMemberId())
                .groupId(newBoard.getGroupId())
                .build();

        System.out.println(tempBoard);

        StudyGroupBoard board = modelMapper.map(tempBoard, StudyGroupBoard.class);
        studyGroupBoardRepository.save(board);

        return modelMapper.map(board, StudyGroupBoardDTO.class);
    }

    @Transactional
    @Override
    public StudyGroupBoardDTO modifyStudyGroupBoard(StudyGroupBoardDTO modifyBoard) {
        // DTO 유효성 검사
        if(!domainStudyGroupBoardService.isValidDTO(RestStatus.PUT,modifyBoard))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        // 기존 엔티티 조회
        StudyGroupBoard existingBoard = studyGroupBoardRepository.findById(modifyBoard.getStudyGroupBoardId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD));

        // 바뀐 제목, 내용, 수정 시각 업데이트
        existingBoard.setTitle(modifyBoard.getTitle());
        existingBoard.setContent(modifyBoard.getContent());
        existingBoard.setUpdatedAt(LocalDateTime.now().withNano(0));

        studyGroupBoardRepository.save(existingBoard);

        return modelMapper.map(existingBoard, StudyGroupBoardDTO.class);
    }

    @Transactional
    @Override
    public void deleteStudyGroupBoard(Long boardId) {
        // 기존 엔티티 조회
        StudyGroupBoard deleteBoard = studyGroupBoardRepository.findById(boardId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD));

        // 유효성 검사
        if(!domainStudyGroupBoardService.isActive(deleteBoard.getActiveStatus()))
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);

        // INACTIVE 처리
        deleteBoard.setActiveStatus(StudyGroupBoardStatus.INACTIVE);
        studyGroupBoardRepository.save(deleteBoard);
    }

    // 좋아요
    @Transactional
    @Override
    public StudyGroupBoardDTO registStudyGroupBoardLike(StudyGroupBoardLikeDTO like) {
        // 기존 엔티티 조회
        StudyGroupBoard existingBoard = studyGroupBoardRepository.findById(like.getStudyGroupBoardId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD));

        // 좋아요 생성 요청
        infraStudyGroupBoardService.registStudyGroupBoardLike(like);

        // 좋아요 수 + 1
        existingBoard.setLikes(existingBoard.getLikes() + 1);
        studyGroupBoardRepository.save(existingBoard);

        return modelMapper.map(existingBoard, StudyGroupBoardDTO.class);
    }

    // 좋아요 취소
    @Transactional
    @Override
    public StudyGroupBoardDTO deleteStudyGroupBoardLike(Long boardId, Long memberId) {
        // 기존 엔티티 조회
        StudyGroupBoard existingBoard = studyGroupBoardRepository.findById(boardId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD));

        // 좋아요 취소 요청
        infraStudyGroupBoardService.deleteStudyGroupBoardLike(boardId, memberId);

        // 좋아요 수 - 1
        existingBoard.setLikes(existingBoard.getLikes() - 1);
        studyGroupBoardRepository.save(existingBoard);

        return modelMapper.map(existingBoard, StudyGroupBoardDTO.class);
    }

}
