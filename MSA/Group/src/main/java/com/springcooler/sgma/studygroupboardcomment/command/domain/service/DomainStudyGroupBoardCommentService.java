package com.springcooler.sgma.studygroupboardcomment.command.domain.service;

import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.StudyGroupBoardCommentStatus;
import com.springcooler.sgma.studygroupboardcomment.command.application.dto.StudyGroupBoardCommentDTO;

public interface DomainStudyGroupBoardCommentService {

    boolean isValidDTO(RestStatus restStatus, StudyGroupBoardCommentDTO studyGroupBoardCommentDTO);

    boolean isActive(StudyGroupBoardCommentStatus activeStatus);

}
