package com.springcooler.sgma.studygroupboardreply.command.domain.service;

import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboardreply.command.application.dto.StudyGroupBoardReplyDTO;
import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.StudyGroupBoardReplyStatus;

public interface DomainStudyGroupBoardReplyService {

    boolean isValidDTO(RestStatus restStatus, StudyGroupBoardReplyDTO studyGroupBoardReplyDTO);

    boolean isActive(StudyGroupBoardReplyStatus activeStatus);

}
