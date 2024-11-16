package com.springcooler.sgma.studygroupnotice.command.domain.service;

import com.springcooler.sgma.studygroupnotice.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupnotice.command.application.dto.StudyGroupNoticeDTO;
import com.springcooler.sgma.studygroupnotice.command.domain.aggregate.StudyGroupNoticeStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyGroupNoticeServiceImpl implements DomainStudyGroupNoticeService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyGroupNoticeDTO studyGroupNoticeDTO) {
        if(studyGroupNoticeDTO == null) return false;
        switch (restStatus) {
            case POST:
                if(studyGroupNoticeDTO.getTitle() == null ||
                        studyGroupNoticeDTO.getContent() == null ||
                        studyGroupNoticeDTO.getGroupId() == null) return false;
                break;
            case PUT:
                if(studyGroupNoticeDTO.getNoticeId() == null ||
                        studyGroupNoticeDTO.getTitle() == null ||
                        studyGroupNoticeDTO.getContent() == null) return false;
                break;
        }

        return true;
    }

    @Override
    public boolean isActive(StudyGroupNoticeStatus activeStatus) {
        return activeStatus.equals(StudyGroupNoticeStatus.ACTIVE);
    }

}
