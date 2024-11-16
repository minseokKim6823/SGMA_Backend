package com.springcooler.sgma.studygroupboard.command.infrastructure.service;

import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;

public interface InfraStudyGroupBoardService {

    void registStudyGroupBoardLike(StudyGroupBoardLikeDTO like);

    void deleteStudyGroupBoardLike(Long boardId, Long memberId);

}
