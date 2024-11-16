package com.springcooler.sgma.studygroupboard.command.domain.service;

import com.springcooler.sgma.studygroupboard.command.application.dto.StudyGroupBoardDTO;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.StudyGroupBoardStatus;

public interface DomainStudyGroupBoardService {

    boolean isValidDTO(RestStatus restStatus, StudyGroupBoardDTO studyGroupBoardDTO);

    boolean isActive(StudyGroupBoardStatus activeStatus);

}
