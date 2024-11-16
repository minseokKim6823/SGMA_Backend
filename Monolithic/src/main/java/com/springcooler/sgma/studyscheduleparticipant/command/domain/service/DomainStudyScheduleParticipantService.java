package com.springcooler.sgma.studyscheduleparticipant.command.domain.service;

import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studyscheduleparticipant.command.application.dto.StudyScheduleParticipantDTO;

public interface DomainStudyScheduleParticipantService {

    boolean isValidDTO(RestStatus restStatus, StudyScheduleParticipantDTO studyScheduleParticipant);
}
