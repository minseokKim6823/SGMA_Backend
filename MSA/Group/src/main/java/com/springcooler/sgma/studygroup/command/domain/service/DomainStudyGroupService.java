package com.springcooler.sgma.studygroup.command.domain.service;

import com.springcooler.sgma.studygroup.command.application.dto.StudyGroupDTO;
import com.springcooler.sgma.studygroup.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroup.command.domain.aggregate.StudyGroupStatus;

public interface DomainStudyGroupService {

    boolean isValidDTO(RestStatus restStatus, StudyGroupDTO studyGroupDTO);

    boolean isActive(StudyGroupStatus groupStatus);

}
