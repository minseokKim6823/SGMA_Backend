package com.springcooler.sgma.studygroupboard.command.infrastructure.service;

import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;
import com.springcooler.sgma.studygroupboardlike.command.application.service.AppStudyGroupBoardLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InfraStudyGroupBoardServiceImpl implements InfraStudyGroupBoardService {

    private final AppStudyGroupBoardLikeService studyGroupBoardLikeService;

    @Autowired
    public InfraStudyGroupBoardServiceImpl(AppStudyGroupBoardLikeService studyGroupBoardLikeService) {
        this.studyGroupBoardLikeService = studyGroupBoardLikeService;
    }

    @Transactional
    @Override
    public void registStudyGroupBoardLike(StudyGroupBoardLikeDTO like) {
        studyGroupBoardLikeService.registStudyGroupBoardLike(like);
    }

    @Transactional
    @Override
    public void deleteStudyGroupBoardLike(Long boardId, Long memberId) {
        studyGroupBoardLikeService.deleteStudyGroupBoardLike(boardId, memberId);
    }

}
