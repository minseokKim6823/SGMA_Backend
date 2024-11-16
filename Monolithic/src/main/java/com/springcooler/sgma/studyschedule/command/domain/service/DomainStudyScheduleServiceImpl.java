package com.springcooler.sgma.studyschedule.command.domain.service;

import com.springcooler.sgma.studyschedule.command.application.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudyScheduleStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyScheduleServiceImpl implements DomainStudyScheduleService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyScheduleDTO studyScheduleDTO) {
        if(studyScheduleDTO == null) return false;
        switch (restStatus) {
            case POST:
                if(studyScheduleDTO.getTitle() == null ||
                        studyScheduleDTO.getContent() == null ||
                        studyScheduleDTO.getScheduleStartTime() == null ||
                        studyScheduleDTO.getScheduleEndTime() == null ||
                        studyScheduleDTO.getGroupId() == null) {
                    return false;
                }
                break;
            case PUT:
                if(studyScheduleDTO.getScheduleId() == null ||
                        studyScheduleDTO.getTitle() == null ||
                        studyScheduleDTO.getContent() == null
                ) return false;
                break;
        }

        return true;
    }

    @Override
    public boolean isActive(StudyScheduleStatus activeStatus) {
        return activeStatus.equals(StudyScheduleStatus.ACTIVE);
    }

}
