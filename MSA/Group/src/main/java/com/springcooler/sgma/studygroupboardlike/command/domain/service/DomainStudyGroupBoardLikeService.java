package com.springcooler.sgma.studygroupboardlike.command.domain.service;

import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;

public interface DomainStudyGroupBoardLikeService {

    boolean isValidDTO(RestStatus restStatus, StudyGroupBoardLikeDTO studyGroupBoardLikeDTO);

}
