package com.springcooler.sgma.studygroupmember.command.domain.service;

import com.springcooler.sgma.studygroupmember.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.StudyGroupMemberStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyGroupMemberServiceImpl implements DomainStudyGroupMemberService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyGroupMemberDTO studyGroupMemberDTO) {
        if(studyGroupMemberDTO == null) return false;
        switch (restStatus) {
            case POST, PUT:
                if(studyGroupMemberDTO.getGroupId() == null ||
                        studyGroupMemberDTO.getUserId() == null) return false;
                break;
        }

        return true;
    }

    @Override
    public boolean isActive(StudyGroupMemberStatus memberStatus) {
        return memberStatus.equals(StudyGroupMemberStatus.ACTIVE);
    }

}
