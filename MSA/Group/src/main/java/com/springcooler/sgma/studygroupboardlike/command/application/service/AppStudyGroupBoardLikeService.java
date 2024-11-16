package com.springcooler.sgma.studygroupboardlike.command.application.service;

import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;

public interface AppStudyGroupBoardLikeService {

    StudyGroupBoardLikeDTO registStudyGroupBoardLike(StudyGroupBoardLikeDTO newLike);

    void deleteStudyGroupBoardLike(Long boardId, Long memberId);

}
