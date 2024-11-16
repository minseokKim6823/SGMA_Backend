package com.springcooler.sgma.studyscheduleparticipant.command.domain.service;

import com.springcooler.sgma.studyscheduleparticipant.command.application.dto.StudyScheduleParticipantDTO;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.RestStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyScheduleParticipantServiceImpl implements DomainStudyScheduleParticipantService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyScheduleParticipantDTO studyScheduleParticipantDTO) {
        if(studyScheduleParticipantDTO == null) return false;
        switch (restStatus) {
            case POST, PUT:
                if (studyScheduleParticipantDTO.getScheduleId() == null ||
                        studyScheduleParticipantDTO.getMemberId() == null)
                    return false;
                break;
        }
        return true;
    }
}
