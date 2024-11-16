package com.springcooler.sgma.studygroup.command.domain.service;

import com.springcooler.sgma.studygroup.command.application.dto.StudyGroupDTO;
import com.springcooler.sgma.studygroup.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroup.command.domain.aggregate.StudyGroupStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyGroupServiceImpl implements DomainStudyGroupService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyGroupDTO studyGroupDTO) {
        if(studyGroupDTO == null) return false;
        switch (restStatus) {
            case POST:
                if(studyGroupDTO.getGroupName() == null ||
                    studyGroupDTO.getUserId() == null ||
                    studyGroupDTO.getStudyGroupCategoryId() == null) return false;
                break;
            case PUT:
                if(studyGroupDTO.getGroupId() == null ||
                    studyGroupDTO.getGroupName() == null ||
                    studyGroupDTO.getStudyGroupCategoryId() == null) return false;
                break;
            case PATCH:
                if(studyGroupDTO.getGroupId() == null ||
                        (studyGroupDTO.getGroupName() == null &&
                        studyGroupDTO.getStudyGroupCategoryId() == null)) return false;
                break;
        }

        return true;
    }

    @Override
    public boolean isActive(StudyGroupStatus groupStatus) {
        return groupStatus.equals(StudyGroupStatus.ACTIVE);
    }

}
