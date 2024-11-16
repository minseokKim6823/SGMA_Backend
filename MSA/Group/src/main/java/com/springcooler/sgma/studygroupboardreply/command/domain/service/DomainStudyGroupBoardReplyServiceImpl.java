package com.springcooler.sgma.studygroupboardreply.command.domain.service;

import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboardreply.command.application.dto.StudyGroupBoardReplyDTO;
import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.StudyGroupBoardReplyStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyGroupBoardReplyServiceImpl implements DomainStudyGroupBoardReplyService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyGroupBoardReplyDTO studyGroupBoardReplyDTO) {
        if(studyGroupBoardReplyDTO == null) return false;
        switch (restStatus) {
            case POST:
                if(studyGroupBoardReplyDTO.getContent() == null ||
                        studyGroupBoardReplyDTO.getMemberId() == null ||
                        studyGroupBoardReplyDTO.getStudyGroupBoardCommentId() == null) return false;
                break;
            case PUT:
                if(studyGroupBoardReplyDTO.getStudyGroupBoardReplyId() == null ||
                        studyGroupBoardReplyDTO.getContent() == null) return false;
                break;
        }

        return true;
    }

    @Override
    public boolean isActive(StudyGroupBoardReplyStatus activeStatus) {
        return activeStatus.equals(StudyGroupBoardReplyStatus.ACTIVE);
    }

}
