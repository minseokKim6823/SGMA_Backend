package com.springcooler.sgma.studygroupboardcomment.command.domain.service;

import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.StudyGroupBoardCommentStatus;
import com.springcooler.sgma.studygroupboardcomment.command.application.dto.StudyGroupBoardCommentDTO;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyGroupBoardCommentServiceImpl implements DomainStudyGroupBoardCommentService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyGroupBoardCommentDTO studyGroupBoardCommentDTO) {
        if(studyGroupBoardCommentDTO == null) return false;
        switch (restStatus) {
            case POST:
                if(studyGroupBoardCommentDTO.getContent() == null ||
                        studyGroupBoardCommentDTO.getMemberId() == null ||
                        studyGroupBoardCommentDTO.getStudyGroupBoardId() == null) return false;
                break;
            case PUT:
                if(studyGroupBoardCommentDTO.getStudyGroupBoardCommentId() == null ||
                        studyGroupBoardCommentDTO.getContent() == null) return false;
                break;
        }

        return true;
    }

    @Override
    public boolean isActive(StudyGroupBoardCommentStatus activeStatus) {
        return activeStatus.equals(StudyGroupBoardCommentStatus.ACTIVE);
    }

}
