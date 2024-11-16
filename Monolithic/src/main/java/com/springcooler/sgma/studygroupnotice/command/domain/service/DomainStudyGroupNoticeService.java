package com.springcooler.sgma.studygroupnotice.command.domain.service;

import com.springcooler.sgma.studygroupnotice.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupnotice.command.application.dto.StudyGroupNoticeDTO;
import com.springcooler.sgma.studygroupnotice.command.domain.aggregate.StudyGroupNoticeStatus;

public interface DomainStudyGroupNoticeService {

    boolean isValidDTO(RestStatus restStatus, StudyGroupNoticeDTO studyGroupNoticeDTO);

    boolean isActive(StudyGroupNoticeStatus activeStatus);

}
