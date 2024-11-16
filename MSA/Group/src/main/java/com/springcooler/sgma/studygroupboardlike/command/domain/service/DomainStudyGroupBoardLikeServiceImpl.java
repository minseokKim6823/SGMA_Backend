package com.springcooler.sgma.studygroupboardlike.command.domain.service;

import com.springcooler.sgma.studygroupboardlike.command.application.dto.StudyGroupBoardLikeDTO;
import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.RestStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyGroupBoardLikeServiceImpl implements DomainStudyGroupBoardLikeService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyGroupBoardLikeDTO studyGroupBoardLikeDTO) {
        if(studyGroupBoardLikeDTO == null) return false;
        switch (restStatus) {
            case POST:
                if(studyGroupBoardLikeDTO.getStudyGroupBoardId() == null ||
                        studyGroupBoardLikeDTO.getMemberId() == null) return false;
                break;
        }

        return true;
    }

}
