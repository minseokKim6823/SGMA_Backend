package com.springcooler.sgma.studyschedule.command.domain.service;

import com.springcooler.sgma.studyschedule.command.application.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudyScheduleStatus;

public interface DomainStudyScheduleService {

    boolean isValidDTO(RestStatus restStatus, StudyScheduleDTO studyScheduleDTO);

    boolean isActive(StudyScheduleStatus activeStatus);
}
