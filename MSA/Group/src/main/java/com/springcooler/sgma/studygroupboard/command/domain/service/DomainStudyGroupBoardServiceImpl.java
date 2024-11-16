package com.springcooler.sgma.studygroupboard.command.domain.service;

import com.springcooler.sgma.studygroupboard.command.application.dto.StudyGroupBoardDTO;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboard.command.domain.aggregate.StudyGroupBoardStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyGroupBoardServiceImpl implements DomainStudyGroupBoardService{

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyGroupBoardDTO studyGroupBoardDTO) {
        if(studyGroupBoardDTO == null) return false;
        switch (restStatus) {
            case POST:
                if(studyGroupBoardDTO.getTitle() == null ||
                        studyGroupBoardDTO.getContent() == null ||
                        studyGroupBoardDTO.getMemberId() == null ||
                        studyGroupBoardDTO.getGroupId() == null) return false;
                break;
            case PUT:
                if(studyGroupBoardDTO.getStudyGroupBoardId() == null ||
                        studyGroupBoardDTO.getTitle() == null ||
                        studyGroupBoardDTO.getContent() == null) return false;
                break;
        }

        return true;
    }

    @Override
    public boolean isActive(StudyGroupBoardStatus activeStatus) {
        return activeStatus.equals(StudyGroupBoardStatus.ACTIVE);
    }

}
