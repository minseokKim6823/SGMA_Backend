package com.springcooler.sgma.studygroupboard.command.application.service;

import com.springcooler.sgma.studygroupboard.command.application.dto.StudyGroupBoardDTO;
import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;

public interface AppStudyGroupBoardService {

    StudyGroupBoardDTO registStudyGroupBoard(StudyGroupBoardDTO newBoard);

    StudyGroupBoardDTO modifyStudyGroupBoard(StudyGroupBoardDTO modifyBoard);

    void deleteStudyGroupBoard(Long boardId);

    StudyGroupBoardDTO registStudyGroupBoardLike(StudyGroupBoardLikeDTO like);

    StudyGroupBoardDTO deleteStudyGroupBoardLike(Long boardId, Long memberId);

}
